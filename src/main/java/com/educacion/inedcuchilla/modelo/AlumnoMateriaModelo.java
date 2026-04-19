package com.educacion.inedcuchilla.modelo;

import jakarta.persistence.*;
@Entity
@Table(name = "alumno_materia")
public class AlumnoMateriaModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alumno_materia")
    private int idAlumnoMateria;

    @ManyToOne
    @JoinColumn(name = "fk_id_alumno", nullable = false)
    private AlumnoModelo alumno;

    @ManyToOne
    @JoinColumn(name = "fk_id_materia", nullable = false)
    private MateriasModelo materia;

    public int getIdAlumnoMateria() {
        return idAlumnoMateria;
    }

    public void setIdAlumnoMateria(int idAlumnoMateria) {
        this.idAlumnoMateria = idAlumnoMateria;
    }

    public AlumnoModelo getAlumno() {
        return alumno;
    }

    public void setAlumno(AlumnoModelo alumno) {
        this.alumno = alumno;
    }

    public MateriasModelo getMateria() {
        return materia;
    }

    public void setMateria(MateriasModelo materia) {
        this.materia = materia;
    }
}
