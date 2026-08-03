package com.educacion.inedcuchilla.Servicio;

import com.educacion.inedcuchilla.DTO.Alumnos.*;
import com.educacion.inedcuchilla.Funciones.Fechas;
import com.educacion.inedcuchilla.modelo.*;
import com.educacion.inedcuchilla.repositorio.*;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.text.Normalizer;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AlumnoServicio {
    private final UsuarioRepositorio usuarioRepositorio;
    private final AlumnoRepositorio alumnoRepositorio;
    private final RolRepositorio rolRepositorio;
    private final UsuarioRolRepositorio usuarioRolRepositorio;
    private final AlumnoServicioJDBC alumnoServicioJDBC;
    private final PasswordEncoder passwordEncoder;
    private final GradoAcademicoRepositorio gradoAcademicoRepositorio;
    private final CicloEscolarRepositorio cicloEscolarRepositorio;

    public AlumnoServicio(UsuarioRepositorio usuarioRepositorio,
                          AlumnoRepositorio alumnoRepositorio,
                          RolRepositorio rolRepositorio,
                          UsuarioRolRepositorio usuarioRolRepositorio,
                          AlumnoServicioJDBC alumnoServicioJDBC,
                          PasswordEncoder passwordEncoder,
                          GradoAcademicoRepositorio gradoAcademicoRepositorio,
                          CicloEscolarRepositorio cicloEscolarRepositorio){
        this.usuarioRepositorio = usuarioRepositorio;
        this.alumnoRepositorio = alumnoRepositorio;
        this.rolRepositorio = rolRepositorio;
        this.usuarioRolRepositorio = usuarioRolRepositorio;
        this.alumnoServicioJDBC = alumnoServicioJDBC;
        this.passwordEncoder = passwordEncoder;
        this.gradoAcademicoRepositorio = gradoAcademicoRepositorio;
        this.cicloEscolarRepositorio = cicloEscolarRepositorio;
    }


    @Transactional
    public ResponseEntity<Map<String, Object>> crearAlumnoUsuario(AlumnoUsuarioRequestDTO alumnoUsuario){
        Map<String, Object> respuesta = new HashMap<>();

        if (usuarioRepositorio.existsByNombreUsuario(alumnoUsuario.nombreUsuario())){
            respuesta.put("MENSAJE", "el usuario que intentas crear como alumno ya existe.");
            respuesta.put("COMO PROCEDER", "puedes utilizar la opcion de convertir usuario a un alumno o crear un alumno con otras credenciales." );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

        AlumnoModelo alumnoNuevo = new AlumnoModelo();
        UsuarioModelo usuarioNuevo = new UsuarioModelo();
        RolModelo rol = rolRepositorio.findByIdRol(4);
        UsuarioRolModelo usuarioRol = new UsuarioRolModelo();

        usuarioNuevo.setNombreUsuario(alumnoUsuario.nombreUsuario());
        usuarioNuevo.setNombre(alumnoUsuario.nombre());
        usuarioNuevo.setApellido(alumnoUsuario.apellido());
        usuarioNuevo.setEmail(alumnoUsuario.email());
        usuarioNuevo.setTelefono(alumnoUsuario.telefono());
        usuarioNuevo.setActivo(true);
        usuarioNuevo.setFechaNacimiento(alumnoUsuario.fechaNacimiento());
        usuarioNuevo.setContrasenia(passwordEncoder.encode(alumnoUsuario.contrasenia()));

        UsuarioModelo usuarioGuardado = usuarioRepositorio.save(usuarioNuevo);

        alumnoNuevo.setCodigoAlumno(alumnoUsuario.codigoAlumno());
        alumnoNuevo.setGenero(alumnoUsuario.genero());
        alumnoNuevo.setActivo(true);
        alumnoNuevo.setUsuario(usuarioGuardado);


        AlumnoModelo alumnoGuardado = alumnoRepositorio.save(alumnoNuevo);
        usuarioRol.setRoles(rol);
        usuarioRol.setUsuario(usuarioGuardado);
        usuarioRolRepositorio.save(usuarioRol);


        AlumnoResponseDTO alumnoResponse = new AlumnoResponseDTO(
                usuarioGuardado.getNombreUsuario(),
                usuarioGuardado.getNombre(),
                usuarioGuardado.getEmail(),
                usuarioGuardado.getTelefono(),
                alumnoGuardado.getCodigoAlumno()
        );

        respuesta.put("MENSAJE", "El usuario se guardo correctamente.");
        respuesta.put("ALUMNO", alumnoResponse);

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    public ResponseEntity<Map<String, Object>> convertirAlumno(ConvertirAlumnoDTO usuarioAlumno){
        Map<String, Object> respuesta = new HashMap<>();
        Optional<UsuarioModelo> usuario = usuarioRepositorio.findByNombreUsuario(usuarioAlumno.nombreUsuario());

        if (usuario.isEmpty()){
            respuesta.put("MENSAJE", "El usuario ingresado no existe.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

        UsuarioRolModelo usuarioRol = new UsuarioRolModelo();
        RolModelo rol = rolRepositorio.findByIdRol(4);

        usuarioRol.setUsuario(usuario.get());
        usuarioRol.setRoles(rol);

        usuarioRolRepositorio.save(usuarioRol);

        AlumnoModelo alumno = new AlumnoModelo();

        alumno.setActivo(true);
        alumno.setGenero(usuarioAlumno.genero());
        alumno.setCodigoAlumno(usuarioAlumno.codigoAlumno());
        alumno.setUsuario(usuario.get());
        AlumnoModelo alumnoGuardado = alumnoRepositorio.save(alumno);

        AlumnoResponseDTO alumnoResponse = new AlumnoResponseDTO(
                usuario.get().getNombreUsuario(),
                usuario.get().getNombre(),
                usuario.get().getEmail(),
                usuario.get().getTelefono(),
                alumnoGuardado.getCodigoAlumno()
        );

        respuesta.put("MENSAJE", "El usuario se guardo correctamente como alumno.");
        respuesta.put("ALUMNO", alumnoResponse);

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    public ResponseEntity<Map<String, Object>> listarAlumnos(){
        Map<String, Object> respuesta = new HashMap<>();
        List<AlumnoListas> alumnos = alumnoServicioJDBC.listarAlumnos();
        if (alumnos.isEmpty()){
            respuesta.put("MENSAJE", "No existe ningun alumno registrado, ingresre uno y vuelva a intentar.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

        respuesta.put("ALUMNOS: ", alumnos);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }


    public ResponseEntity<Map<String, Object>> cargarMasivo(MultipartFile archivo) {

        Map<String, Object> respuesta = new HashMap<>();
        List<UsuarioModelo> listaUsuarios = new ArrayList<>();
        RolModelo rolAlumno = rolRepositorio.findByIdRol(4);
        List<AlumnoResponseDTO> errorAlumnos = new ArrayList<>();
        CicloEscolarModelo cicloEscolar = cicloEscolarRepositorio.findByAnio(java.time.Year.now().getValue());
        int guardados;
        int noGuardados = 0;

        try (PDDocument documento = Loader.loadPDF(archivo.getBytes())) {
            Pattern pattern = Pattern.compile("\\d+\\s+([A-Z0-9]+)\\s+(.*?)\\s+(\\d{2}/\\d{2}/\\d{4})\\s+[A-Za-zÁÉÍÓÚáéíóúÑñ]+\\s+CUI\\s+\\d+\\s+([A-Z]+)", Pattern.DOTALL);

            PDFTextStripper stripper = new PDFTextStripper();
            String clase = archivo.getOriginalFilename();
            int idGrado = validarSeccion(clase);


            if (idGrado == 0){
                respuesta.put("MENSAJE", "El nombre del archivo es incorrecto, coloque segun se indico");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
            }

            Optional<GradoAcademicoModelo> grado = gradoAcademicoRepositorio.findById(idGrado);

            if (grado.isEmpty()){
                respuesta.put("MENSAJE", "El grado ingresado no existe.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
            }

            for (int i = 1; i <= documento.getNumberOfPages(); i++) {
                stripper.setStartPage(i);
                stripper.setEndPage(i);

                String texto = stripper.getText(documento);

                texto = texto.replaceAll("\\r\\n", "\n");
                texto = texto.replaceAll("\\n(?!\\d{2}/\\d{2}/\\d{4})", " ");
                texto = texto.replaceAll("(?m)^\\d+\\s*$", "");
                texto = texto.replaceAll("Página\\s+\\d+\\s+de\\s+\\d+", "");

                Matcher matcher = pattern.matcher(texto);

                while (matcher.find()) {
                    String codigo = matcher.group(1);
                    String nombre = matcher.group(2)
                            .replaceAll("\\s+", " ")
                            .trim();
                    String fecha = matcher.group(3);
                    String genero = matcher.group(4);

                    FormatoPdfDTO formato = new FormatoPdfDTO(
                      codigo,
                      nombre,
                      fecha,
                      genero
                    );

                    try {
                        AlumnoModelo alumnoNuevo = formatearAlumno(formato);
                        UsuarioModelo usuarioNuevo = formatearUsuario(formato);

                        usuarioNuevo.setAlumno(alumnoNuevo);

                        UsuarioRolModelo usuarioRol = new UsuarioRolModelo();
                        usuarioRol.setUsuario(usuarioNuevo);
                        usuarioRol.setRoles(rolAlumno);

                        usuarioNuevo.getUsuarioRolModelo().add(usuarioRol);
                        alumnoNuevo.setUsuario(usuarioNuevo);

                        InscripcionModelo inscripcion = new InscripcionModelo();
                        inscripcion.setAlumno(alumnoNuevo);
                        inscripcion.setGradoAcademico(grado.get());
                        inscripcion.setInscripcionActiva(true);
                        inscripcion.setCicloEscolar(cicloEscolar);
                        alumnoNuevo.getInscripciones().add(inscripcion);

                        listaUsuarios.add(usuarioNuevo);
                    }catch (Exception e){

                        AlumnoResponseDTO erroresEnAlumno = new AlumnoResponseDTO(
                                codigo,
                                nombre,
                                "",
                                "",
                                ""
                        );

                        errorAlumnos.add(erroresEnAlumno);
                        noGuardados++;

                        System.out.println(e.getCause() + " " + e.getMessage());

                    }

                }
            }

            usuarioRepositorio.saveAll(listaUsuarios);
            guardados = listaUsuarios.size();

        }
        catch (Exception e) {
            respuesta.put("MENSAJE", "Existe un error al leer el pdf.");
            System.out.println(e.getCause() + " " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(respuesta);
        }


        respuesta.put("GUARDADOS", guardados + " " + "Alumnos Guardados correctamente.");
        respuesta.put("ALUMNOS_NO_GUARDADOS: ", errorAlumnos);
        respuesta.put("NO_GUARDADOS: ", noGuardados + " " + "Alumno no se guardaron.");
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    public AlumnoModelo formatearAlumno(FormatoPdfDTO formato){
        AlumnoModelo alumnoNuevo = new AlumnoModelo();
        alumnoNuevo.setCodigoAlumno(formato.codigo());
        alumnoNuevo.setGenero(formato.genero());
        alumnoNuevo.setActivo(true);

        return alumnoNuevo;
    }

    public UsuarioModelo formatearUsuario(FormatoPdfDTO formato){

        Fechas formatearFecha = new Fechas();
        Map<String, Object> nombreCompleto = corregirNombres(formato.nombre());

        UsuarioModelo usuarioNuevo = new UsuarioModelo();
        usuarioNuevo.setNombreUsuario(formato.codigo());
        usuarioNuevo.setNombre(String.join(" ", (List<String>) nombreCompleto.get("NOMBRES")));
        usuarioNuevo.setApellido(String.join(" ", (List<String>) nombreCompleto.get("APELLIDOS")));
        usuarioNuevo.setEmail(formato.codigo() + "@email.com");
        usuarioNuevo.setTelefono("");
        usuarioNuevo.setActivo(true);
        usuarioNuevo.setFechaNacimiento(formatearFecha.formatearFecha(formato.fecha()));
        usuarioNuevo.setContrasenia(passwordEncoder.encode(limpiarContrasenia(formato.codigo())));

        return usuarioNuevo;
    }

    public int validarSeccion(String nombreArchivo) {

        Map<String, Integer> clasesActivas = Map.ofEntries(
                Map.entry("4CA.pdf", 1),
                Map.entry("4CB.pdf", 2),
                Map.entry("4CC.pdf", 3),
                Map.entry("4MA.pdf", 4),
                Map.entry("4MB.pdf", 5),
                Map.entry("4MC.pdf", 6),
                Map.entry("5CA.pdf", 7),
                Map.entry("5CB.pdf", 8),
                Map.entry("5CC.pdf", 9),
                Map.entry("5MA.pdf", 10),
                Map.entry("5MB.pdf", 11),
                Map.entry("5MC.pdf", 12)
        );

        return clasesActivas.getOrDefault(nombreArchivo, 0);
    }

    public Map<String, Object> corregirNombres(String nombreAlumno) {
            Map<String, Object> nombreCompleto = new HashMap<>();
            Set<String> apellidosCompuestos = Set.of(
                    "DE",
                    "DEL",
                    "LA",
                    "LAS",
                    "LOS",
                    "SAN",
                    "SANTA",
                    "VAN",
                    "VON"
            );


            List<String> palabras = new ArrayList<>();

            String[] partesDeNombre = nombreAlumno.split("\\s+");

            for (int i = 0; i < partesDeNombre.length; i++) {

                if (apellidosCompuestos.contains(partesDeNombre[i])
                        && i + 1 < partesDeNombre.length) {

                    palabras.add(partesDeNombre[i] + " " + partesDeNombre[i + 1]);
                    i++;

                } else {

                    palabras.add(partesDeNombre[i]);

                }
            }

            List<String> apellidos = new ArrayList<>();
            List<String> nombresAlumno = new ArrayList<>();

            // Los dos primeros elementos son los apellidos
            if (palabras.size() >= 2) {
                apellidos.add(palabras.get(0));
                apellidos.add(palabras.get(1));
            }

            // El resto son nombres
            for (int i = 2; i < palabras.size(); i++) {
                nombresAlumno.add(palabras.get(i));
            }

            nombreCompleto.put("NOMBRES", nombresAlumno);
            nombreCompleto.put("APELLIDOS", apellidos);

            return nombreCompleto;



        }

    public String limpiarContrasenia(String contraseniaLimpiada){

        contraseniaLimpiada = Normalizer.normalize(contraseniaLimpiada, Normalizer.Form.NFD);
        contraseniaLimpiada = contraseniaLimpiada.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        contraseniaLimpiada = contraseniaLimpiada.replaceAll("\\s+", "");
        contraseniaLimpiada = contraseniaLimpiada.toLowerCase().trim();

        return contraseniaLimpiada;
    }



    }
