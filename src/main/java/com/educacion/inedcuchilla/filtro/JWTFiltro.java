package com.educacion.inedcuchilla.filtro;

import com.educacion.inedcuchilla.Llaves.JWTLlave;
import com.educacion.inedcuchilla.modelo.UsuarioModelo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tools.jackson.core.exc.StreamReadException;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JWTFiltro extends UsernamePasswordAuthenticationFilter {


    private AuthenticationManager authenticationManager;


    public JWTFiltro(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException{

        UsuarioModelo usuario;
        String nombreUsuario;
        String contrasenia;

        try {
            usuario = new ObjectMapper().readValue(request.getInputStream(), UsuarioModelo.class);
            nombreUsuario = usuario.getNombreUsuario();
            contrasenia = usuario.getContrasenia();

        } catch (StreamReadException e) {
            throw new RuntimeException(e);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                nombreUsuario,
                contrasenia
        );

        return authenticationManager.authenticate(authenticationToken);

    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {

        User usuario = (User) authResult.getPrincipal();

        List<String> roles = authResult.getAuthorities()
                .stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .filter(authority -> authority.startsWith("ROLE_"))
                .toList();

        Claims claims = Jwts.claims()
                .add("roles", roles)
                .build();

        String token = Jwts.builder()
                .subject(usuario.getUsername())
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + 43200000))
                .issuedAt(new Date())
                .signWith(JWTLlave.LLAVE_)
                .compact();

        response.addHeader(JWTLlave.CABECERA, JWTLlave.BEARER + token);

        Map<String, String> cuerpo = new HashMap<>();
        cuerpo.put("token", token);
        cuerpo.put("usuario", usuario.getUsername());
        cuerpo.put("mensaje", String.format("Bienvenido %s", usuario.getUsername()));

        response.getWriter().write(new ObjectMapper().writeValueAsString(cuerpo));
        response.setContentType(JWTLlave.TIPO_CONTENIDO);
        response.setStatus(HttpServletResponse.SC_OK);
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed)
            throws IOException, ServletException {

        Map<String, String> cuerpo = new HashMap<>();
        cuerpo.put("mensaje", "ERROR EN LA AUTENTICACION " + failed.getMessage());
        cuerpo.put("usuario", "credenciales incorrectas. ");
        cuerpo.put("token", String.valueOf(response.getStatus()));

        response.getWriter().write(new ObjectMapper().writeValueAsString(cuerpo));
        response.setContentType(JWTLlave.TIPO_CONTENIDO);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

    }
}
