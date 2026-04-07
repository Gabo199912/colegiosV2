package com.educacion.inedcuchilla.modelo;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "alumno")
public class AlumnoModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alumno")
    private Integer idUsuario;

    @Column(name = "genero")
    private String genero;

    @Column(name = "codigo_alumno")
    private String codigoAlumno;

    @OneToOne
    @JoinColumn(name = "fk_id_usuario")
    private UsuarioModelo usuario;


    @ManyToOne
    @JoinColumn(name = "fk_id_grado")
    private GradoModelo grado;

    @Column(name = "activo")
    private boolean activo;

    public GradoModelo getGrado() {
        return grado;
    }

    public void setGrado(GradoModelo grado) {
        this.grado = grado;
    }


    public AlumnoModelo() {
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCodigoAlumno() {
        return codigoAlumno;
    }

    public void setCodigoAlumno(String codigoAlumno) {
        this.codigoAlumno = codigoAlumno;
    }




    public UsuarioModelo getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModelo usuario) {
        this.usuario = usuario;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
