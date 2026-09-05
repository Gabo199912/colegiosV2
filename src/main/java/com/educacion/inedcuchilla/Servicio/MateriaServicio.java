package com.educacion.inedcuchilla.Servicio;

import com.educacion.inedcuchilla.DTO.Grado.GradoResponse;
import com.educacion.inedcuchilla.DTO.Materias.MateriaRequest;
import com.educacion.inedcuchilla.Modelo.GradoAcademicoMateriaModelo;
import com.educacion.inedcuchilla.Modelo.MaestroMateriaModelo;
import com.educacion.inedcuchilla.Modelo.MaestroModelo;
import com.educacion.inedcuchilla.Modelo.MateriaModelo;
import com.educacion.inedcuchilla.repositorio.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class MateriaServicio {
    private final MateriaRepositorio materiaRepositorio;
    private final SeccionRepositorio seccionRepositorio;
    private final GradoRepositorio gradoRepositorio;
    private final EspecialidadRepositorio especialidadRepositorio;
    private final MateriaServicioJDBC materiaServicioJDBC;
    private final CicloEscolarRepositorio cicloEscolarRepositorio;
    private final GradoAcademicoRepositorio gradoAcademicoRepositorio;
    private final GradoAcademicoMateriaRepositorio gradoAcademicoMateriaRepositorio;
    private final MaestroRepositorio maestroRepositorio;
    private final MaestroMateriaRepositorio maestroMateriaRepositorio;

    public MateriaServicio(MateriaRepositorio materiaRepositorio,
                           SeccionRepositorio seccionRepositorio,
                           GradoRepositorio gradoRepositorio,
                           EspecialidadRepositorio especialidadRepositorio,
                           MateriaServicioJDBC materiaServicioJDBC,
                           CicloEscolarRepositorio cicloEscolarRepositorio,
                           GradoAcademicoRepositorio gradoAcademicoRepositorio,
                           GradoAcademicoMateriaRepositorio gradoAcademicoMateriaRepositorio,
                           MaestroRepositorio maestroRepositorio,
                           MaestroMateriaRepositorio maestroMateriaRepositorio){
        this.materiaRepositorio = materiaRepositorio;
        this.seccionRepositorio = seccionRepositorio;
        this.gradoRepositorio = gradoRepositorio;
        this.especialidadRepositorio = especialidadRepositorio;
        this.materiaServicioJDBC = materiaServicioJDBC;
        this.cicloEscolarRepositorio = cicloEscolarRepositorio;
        this.gradoAcademicoRepositorio = gradoAcademicoRepositorio;
        this.gradoAcademicoMateriaRepositorio = gradoAcademicoMateriaRepositorio;
        this.maestroRepositorio = maestroRepositorio;
        this.maestroMateriaRepositorio = maestroMateriaRepositorio;
    }


    @Transactional
    public ResponseEntity<Map<String, Object>> crearMateria(MateriaRequest materia){
        Map<String,Object> respuesta = new HashMap<>();

        if (materiaRepositorio.existsByNombreMateria(materia.nombreMateria())){
            respuesta.put("MENSAJE", "La materia ingresada ya existe, favor de colocar otra.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }

        if (!gradoRepositorio.existsByGrado(materia.grado())){
            respuesta.put("MENSAJE", "El grado ingresado no existe, favor de crearlo y probar de nuevo.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

        if(!maestroRepositorio.existsByCodigoEmpleado(materia.codigoProfesor())){
            respuesta.put("MENSAJE", "El codigo de profesor ingresado no existe, favor de crearlo y probar de nuevo.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

        List<GradoResponse> listaGrados = materiaServicioJDBC.buscarGrados(materia.especialidad().toUpperCase(), materia.grado().toUpperCase());
        MaestroModelo maestro = maestroRepositorio.findByCodigoEmpleado(materia.codigoProfesor());
        MateriaModelo materiaNueva = new MateriaModelo();
        List<GradoAcademicoMateriaModelo> listaGradoAcademicoMateria = new ArrayList<>();

        materiaNueva.setNombreMateria(materia.nombreMateria());
        MateriaModelo materiaGuardada = materiaRepositorio.save(materiaNueva);

        for (GradoResponse grado: listaGrados){
            GradoAcademicoMateriaModelo gradoAcademicoMateria = new GradoAcademicoMateriaModelo();
            gradoAcademicoMateria.setMateria(materiaGuardada);
            gradoAcademicoMateria.setGradoAcademico(gradoAcademicoRepositorio.findByIdGradoAcademico(grado.idGradoAcademico()));
            listaGradoAcademicoMateria.add(gradoAcademicoMateria);
        }

        MaestroMateriaModelo maestroMateria = new MaestroMateriaModelo();
        maestroMateria.setMaestro(maestro);
        maestroMateria.setMateria(materiaGuardada);

        maestroMateriaRepositorio.save(maestroMateria);

        gradoAcademicoMateriaRepositorio.saveAll(listaGradoAcademicoMateria);
        respuesta.put("MENSAJE", "MATERIA GUARDADA PARA TODOS LOS GRADOS SELECCIONADOS.");
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);

    }

//    public ResponseEntity<Map<String, Object>> crearMateria(MultipartFile archivo) throws IOException {
//        Map<String, Object> respuesta = new HashMap<>();
//        Workbook excelMateria = new XSSFWorkbook(archivo.getInputStream());
//        List<String> materiasCuartoCompu = new ArrayList<>();
//        List<String> materiasCuartoMec = new ArrayList<>();
//        List<String> materiasQuintoCompu = new ArrayList<>();
//        List<String> materiasQuintoMec = new ArrayList<>();
//
//        for (int i = 0; i < excelMateria.getNumberOfSheets(); i++){
//            Sheet hoja = excelMateria.getSheetAt(i);
//
//            for (Row fila : hoja){
//                    if (fila.getRowNum() == 0) continue;
//
//                    if (filaVacia(fila)){
//                        System.out.println("la fila esta vacia, favor llenarla");
//                        break;
//                    }
//
//                    if (fila.getCell(1).getStringCellValue().contains("computacion")){
//                        materiasCuartoCompu.add(fila.getCell(0).getStringCellValue());
//                    }
//
//                    if (fila.getCell(1).getStringCellValue().contains("mecanica")){
//
//                    }
//
//            }
//        }
//
//        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
//
//    }
//
//
//
//    public boolean filaVacia(Row fila){
//        for (int i = 0; i < 4; i++){
//            Cell celda = fila.getCell(i);
//
//            if (celda != null && celda.getCellType() != CellType.BLANK){
//                return false;
//            }
//        }
//        return true;
//    }
}
