package com.educacion.inedcuchilla.DTO;

import com.educacion.inedcuchilla.modelo.AlumnoModelo;
import com.educacion.inedcuchilla.modelo.UsuarioModelo;

public class UsuarioAlumnoDTO {
    public UsuarioModelo usuario;
    public AlumnoModelo alumno;

    public UsuarioModelo getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModelo usuario) {
        this.usuario = usuario;
    }

    public AlumnoModelo getAlumno() {
        return alumno;
    }

    public void setAlumno(AlumnoModelo alumno) {
        this.alumno = alumno;
    }
}
