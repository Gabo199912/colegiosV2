package com.educacion.inedcuchilla.filtro;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.educacion.inedcuchilla.Llaves.JWTLlave.*;

public class ValidacionFiltro extends BasicAuthenticationFilter {

    public ValidacionFiltro(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader(CABECERA);
        if (header == null || !header.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        String token = header.replace(BEARER, "");

        try {
            Claims claims = Jwts.parser().verifyWith(LLAVE_).build().parseClaimsJws(token).getBody();
            String nombreUsuario = claims.getSubject();
            List<String> roles = claims.get("roles", List.class);

            List<GrantedAuthority> authorities = roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .map(authority -> (GrantedAuthority) authority)
                    .toList();

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            nombreUsuario,
                            null,
                            authorities
                    );

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);


        }catch (Exception e) {
            Map<String, String> body = new HashMap<>();
            body.put("mensaje", "ERROR EN LA VALIDACION DEL TOKEN");
            body.put("error", e.getMessage());


            response.getWriter().write(new ObjectMapper().writeValueAsString(body));
            response.setContentType(TIPO_CONTENIDO);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        }
    }
}
