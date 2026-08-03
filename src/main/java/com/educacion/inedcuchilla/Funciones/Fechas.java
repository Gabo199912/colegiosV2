package com.educacion.inedcuchilla.Funciones;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Fechas {

    public LocalDate formatearFecha(String fecha){
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaFormateada = LocalDate.parse(fecha, formateador);
        return fechaFormateada;
    }
}
