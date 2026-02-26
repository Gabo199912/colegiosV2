package com.educacion.inedcuchilla.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "notas")
public class NotasModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idNotas;

    @Column(name = "nota")
    private Double nota;

    @Column(name = "descripcion")
    private String descripcion;


    @ManyToOne
    @JoinColumn(name = "fk_id_usuario")
    private UsuarioModelo usuario;

    @ManyToOne
    @JoinColumn(name = "fk_id_materia")
    private MateriasModelo materia;

    @ManyToOne
    @JoinColumn(name = "fk_id_grado")
    private GradoModelo grado;

    @ManyToOne
    @JoinColumn(name = "fk_id_bimestre")
    private BimestreModelo bimestre;


    public NotasModelo() {
    }

    public int getIdNotas() {
        return idNotas;
    }

    public void setIdNotas(int idNotas) {
        this.idNotas = idNotas;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public UsuarioModelo getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModelo usuario) {
        this.usuario = usuario;
    }

    public MateriasModelo getMateria() {
        return materia;
    }

    public void setMateria(MateriasModelo materia) {
        this.materia = materia;
    }

    public GradoModelo getGrado() {
        return grado;
    }

    public void setGrado(GradoModelo grado) {
        this.grado = grado;
    }

    public BimestreModelo getBimestre() {
        return bimestre;
    }

    public void setBimestre(BimestreModelo bimestre) {
        this.bimestre = bimestre;
    }
}
