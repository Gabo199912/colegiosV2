package com.educacion.inedcuchilla.Modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "maestro_materia")
public class MaestroMateriaModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_maestro_materia")
    private Integer idMaestroMateria;

    @ManyToOne
    @JoinColumn(name = "fk_id_maestro")
    private MaestroModelo maestro;

    @ManyToOne
    @JoinColumn(name = "fk_id_materia")
    private MateriaModelo materia;


    public Integer getIdMaestroMateria() {
        return idMaestroMateria;
    }

    public void setIdMaestroMateria(Integer idMaestroMateria) {
        this.idMaestroMateria = idMaestroMateria;
    }

    public MaestroModelo getMaestro() {
        return maestro;
    }

    public void setMaestro(MaestroModelo maestro) {
        this.maestro = maestro;
    }

    public MateriaModelo getMateria() {
        return materia;
    }

    public void setMateria(MateriaModelo materia) {
        this.materia = materia;
    }
}
