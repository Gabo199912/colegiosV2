package com.educacion.inedcuchilla.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "materia")
public class MateriasModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_materia")
    private Integer idMateria;

    @Column(name = "nombre_materia")
    private String nombreMateria;

    @ManyToOne
    @JoinColumn(name = "fk_id_usuario")
    private UsuarioModelo usuario;

    public MateriasModelo() {
    }

    public Integer getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Integer idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public UsuarioModelo getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModelo usuario) {
        this.usuario = usuario;
    }
}
