package com.educacion.inedcuchilla.Servicio;

import com.educacion.inedcuchilla.DTO.Materias.MateriaRequest;
import com.educacion.inedcuchilla.repositorio.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@Service
public class MateriaServicio {
    private final MateriaRepositorio materiaRepositorio;
    private final SeccionRepositorio seccionRepositorio;
    private final GradoRepositorio gradoRepositorio;
    private final EspecialidadRepositorio especialidadRepositorio;
    private final MateriaServicioJDBC materiaServicioJDBC;
    private final CicloEscolarRepositorio cicloEscolarRepositorio;

    public MateriaServicio(MateriaRepositorio materiaRepositorio,
                           SeccionRepositorio seccionRepositorio,
                           GradoRepositorio gradoRepositorio,
                           EspecialidadRepositorio especialidadRepositorio,
                           MateriaServicioJDBC materiaServicioJDBC,
                           CicloEscolarRepositorio cicloEscolarRepositorio){
        this.materiaRepositorio = materiaRepositorio;
        this.seccionRepositorio = seccionRepositorio;
        this.gradoRepositorio = gradoRepositorio;
        this.especialidadRepositorio = especialidadRepositorio;
        this.materiaServicioJDBC = materiaServicioJDBC;
        this.cicloEscolarRepositorio = cicloEscolarRepositorio;
    }

    public ResponseEntity<Map<String, Object>> crearMateria(MultipartFile archivo) throws IOException {
        Map<String, Object> respuesta = new HashMap<>();
        Workbook excelMateria = new XSSFWorkbook(archivo.getInputStream());
        Set<String> especialidades = new HashSet<>();


        for (int i = 0; i < excelMateria.getNumberOfSheets(); i++){
            Sheet hoja = excelMateria.getSheetAt(i);

            for (Row fila : hoja){
                    if (fila.getRowNum() == 0) continue;

                    if (filaVacia(fila)){
                        System.out.println("la fila esta vacia, favor llenarla");
                        break;
                    }


            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);

    }



    public boolean filaVacia(Row fila){
        for (int i = 0; i < 4; i++){
            Cell celda = fila.getCell(i);

            if (celda != null && celda.getCellType() != CellType.BLANK){
                return false;
            }
        }
        return true;
    }
}
