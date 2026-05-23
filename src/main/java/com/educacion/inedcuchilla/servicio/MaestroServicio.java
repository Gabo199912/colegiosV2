package com.educacion.inedcuchilla.servicio;

import com.educacion.inedcuchilla.DTO.MaestroDTO;
import com.educacion.inedcuchilla.Funciones.Fechas;
import com.educacion.inedcuchilla.modelo.MaestroModelo;
import com.educacion.inedcuchilla.modelo.RolModelo;
import com.educacion.inedcuchilla.modelo.UsuarioModelo;
import com.educacion.inedcuchilla.modelo.UsuarioRolModelo;
import com.educacion.inedcuchilla.repositorio.MaestroRepositorio;
import com.educacion.inedcuchilla.repositorio.RolRepositorio;
import com.educacion.inedcuchilla.repositorio.UsuarioRepositorio;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jspecify.annotations.NonNull;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.Normalizer;
import java.time.LocalDate;
import java.util.*;

@Service
public class MaestroServicio {
    private final MaestroRepositorio maestroRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final RolRepositorio rolRepositorio;
    private final PasswordEncoder passwordEncoder;

    public  MaestroServicio(MaestroRepositorio maestroRepositorio,
                            UsuarioRepositorio usuarioRepositorio,
                            RolRepositorio rolRepositorio,
                            PasswordEncoder passwordEncoder){
        this.maestroRepositorio = maestroRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.rolRepositorio = rolRepositorio;
        this.passwordEncoder = passwordEncoder;
    }

    public Map<String, Object> crearMaestro(@NonNull MaestroModelo maestroModelo){
        if (maestroModelo.getCodigoEmpleado().isEmpty()){
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("MENSAJE", "todos los datos deben de llenarse ");
            return respuesta;
        }

        maestroRepositorio.save(maestroModelo);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("MENSAJE", "MAESTRO AGREGADO CORRECTAMENTE.");
        respuesta.put("MAESTRO: ", maestroModelo);
        return  respuesta;
    }

    public List<MaestroModelo> listarMaestros(){
        List<MaestroModelo> listaMaestros = maestroRepositorio.findAll();
        return listaMaestros;
    }

    @Transactional
    public Map<String, Object> crearUsuarioMaestro(@NonNull MaestroDTO maestroDTO){
        boolean existeEmail = usuarioRepositorio.existsByEmail(maestroDTO.getUsuario().getEmail());
        boolean existeNombreUsuario = usuarioRepositorio.existsByNombreUsuario(maestroDTO.getUsuario().getNombreUsuario());
        RolModelo rol = rolRepositorio.findByTipoUsuario("MAESTRO");
        UsuarioRolModelo usuarioRol = new UsuarioRolModelo();

        if (existeEmail){
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("MENSAJE","el email no debe ir vacio o ya existe");
            respuesta.put("STATUS", HttpStatus.BAD_REQUEST);
            return respuesta;
        }

        if (existeNombreUsuario){
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("MENSAJE","el nombre de usuario no debe ir vacio o ya existe");
            respuesta.put("STATUS", HttpStatus.BAD_REQUEST);
            return respuesta;
        }


        UsuarioModelo usuarioGuardado = usuarioRepositorio.save(maestroDTO.getUsuario());
        usuarioRol.setRoles(rol);
        usuarioRol.setUsuario(usuarioGuardado);
        maestroDTO.getMaestro().setFkIdUsuario(usuarioGuardado);
        maestroRepositorio.save(maestroDTO.getMaestro());

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("MENSAJE", "MAESTRO CREADO CORRECTAMENTE.");
        respuesta.put("STATUS", HttpStatus.OK);
        return respuesta;

    }

    @Transactional
    public Map<String, Object> asignarMaestroUsuario(@NonNull MaestroDTO maestroDTO){
        RolModelo rol = rolRepositorio.findByTipoUsuario("MAESTRO");
        UsuarioRolModelo usuarioRol = new UsuarioRolModelo();



        usuarioRol.setRoles(rol);
        usuarioRol.setUsuario(maestroDTO.getUsuario());
        maestroDTO.getMaestro().setFkIdUsuario(maestroDTO.getUsuario());
        maestroRepositorio.save(maestroDTO.getMaestro());

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("MENSAJE", "ROL MAESTRO ASIGNADO A ." + maestroDTO.getUsuario().getNombre() + " Correctamente.");
        respuesta.put("STATUS", HttpStatus.OK);
        return respuesta;



    }

    public Map<String, Object> cargarMasivo(@NonNull MultipartFile archivo) throws IOException {
        Workbook excelMaestros = new XSSFWorkbook(archivo.getInputStream());
        List<UsuarioModelo> listaUsuarios = new ArrayList<>();
        List<MaestroModelo> listaMaestros = new ArrayList<>();
        Fechas formateadorFechas = new Fechas();
        List<String> errores = new ArrayList<>();
        DataFormatter formateador = new DataFormatter();

        RolModelo rol = rolRepositorio.findByTipoUsuario("MAESTRO");


        int procesados = 0;
        int omitidos = 0;

        Set<String> nombresUsuario = new HashSet<>(usuarioRepositorio.cargarNombrsUsuario());
        Set<String> correosUsuario = new HashSet<>(usuarioRepositorio.cargarCorreo());

        for (int i = 0; i < excelMaestros.getNumberOfSheets(); i++){
            Sheet hoja = excelMaestros.getSheetAt(i);

            for (Row fila: hoja){
                try {
                    if (fila.getRowNum() == 0)continue;

                    UsuarioRolModelo usuarioRol = new UsuarioRolModelo();

                    String nombreUsuario = formateador.formatCellValue(fila.getCell(0));// nombre usuario
                    String nombre = formateador.formatCellValue(fila.getCell(1));// nombre
                    String apellido = formateador.formatCellValue(fila.getCell(2));//apellido
                    String correo = formateador.formatCellValue(fila.getCell(3));//correo
                    String telefono = formateador.formatCellValue(fila.getCell(4));//telefono
                    String contrasenia = formateador.formatCellValue(fila.getCell(7));
                    LocalDate fechas = formateadorFechas.formatearFecha(formateador.formatCellValue(fila.getCell(5)));

                    String contraseniaLimpia = limpiarContrasenia(contrasenia);


                    if (nombresUsuario.contains(nombreUsuario)){
                        errores.add("El usuario: " + nombreUsuario + " ya existe.");
                        omitidos++;
                        continue;
                    }

                    //verifica el correo del usuario
                    if (correosUsuario.contains(correo)){
                        errores.add("el correo: " + correo + " ya existe");
                        omitidos++;
                        continue;
                    }

                    UsuarioModelo usuarioCreado = new UsuarioModelo(
                            nombreUsuario,
                            nombre,
                            apellido,
                            correo,
                            true,
                            telefono,
                            fechas,
                            passwordEncoder.encode(contraseniaLimpia)// contrasenia
                    );

                    MaestroModelo maestroCreado = new MaestroModelo(
                            fila.getCell(7).getStringCellValue(),
                            usuarioCreado
                    );

                    usuarioRol.setRoles(rol);
                    usuarioRol.setUsuario(usuarioCreado);

                    usuarioCreado.getUsuarioRol().add(usuarioRol);
                    listaUsuarios.add(usuarioCreado);
                    listaMaestros.add(maestroCreado);
                    procesados++;

                } catch (Exception e) {
                    Map<String, Object> respuesta = new HashMap<>();
                    respuesta.put("MENSAJE", "ERROR EN AL PROCESAR DATOS DENTRO DEL EXCEL SIGA EL FORMATO DE EJEMPLO.");
                    respuesta.put("SOLUCION", "LLAME A SU TECNICO");
                    System.out.println("error: " + e.getMessage());

                    return respuesta;
                }
            }
        }

        usuarioRepositorio.saveAll(listaUsuarios);
        maestroRepositorio.saveAll(listaMaestros);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("MENSAJE", "se guardaron: " + procesados + " maestros correctamente.");
        respuesta.put("CARGADOS CORRECTAMENTE", procesados);
        respuesta.put("OMITIDOS", omitidos);
        respuesta.put("ERRORES", errores);

        return respuesta;
    }

    public String limpiarContrasenia(String contraseniaLimpiada){

        contraseniaLimpiada = Normalizer.normalize(contraseniaLimpiada, Normalizer.Form.NFD);
        contraseniaLimpiada = contraseniaLimpiada.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        contraseniaLimpiada = contraseniaLimpiada.replaceAll("\\s+", "");
        contraseniaLimpiada = contraseniaLimpiada.toLowerCase().trim();

        return contraseniaLimpiada;
    }



}
