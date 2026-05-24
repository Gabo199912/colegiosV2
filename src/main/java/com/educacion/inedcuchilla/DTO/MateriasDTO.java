package com.educacion.inedcuchilla.DTO;

import com.educacion.inedcuchilla.modelo.MaestroModelo;
import com.educacion.inedcuchilla.modelo.MateriasModelo;

public class MateriasDTO {
    private MateriasModelo materia;
    String codigoMaestro;

    public MateriasModelo getMateria() {
        return materia;
    }

    public void setMateria(MateriasModelo materia) {
        this.materia = materia;
    }

    public String getCodigoMaestro() {
        return codigoMaestro;
    }

    public void setCodigoMaestro(String codigoMaestro) {
        this.codigoMaestro = codigoMaestro;
    }
}
