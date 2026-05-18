package com.educacion.inedcuchilla.repositorio;

import com.educacion.inedcuchilla.DTO.AlumnoDTO;
import com.educacion.inedcuchilla.modelo.AlumnoModelo;
import com.educacion.inedcuchilla.modelo.UsuarioModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlumnoRepositorio extends JpaRepository<AlumnoModelo, Integer> {

    @Query("SELECT new com.educacion.inedcuchilla.DTO.AlumnoDTO(" +
            "u.nombre, u.apellido, u.email, u.telefono, a.genero, g.nombreGrado, g.especialidad, g.seccion) " +
            "FROM AlumnoModelo a " +
            "JOIN a.usuario u " +
            "JOIN a.grado g " +
            "WHERE a.codigoAlumno = :codigoAlumno")
    AlumnoDTO buscarAlumnoPorCodigo(@Param("codigoAlumno") String codigoAlumno);

    @Query("SELECT new com.educacion.inedcuchilla.DTO.AlumnoDTO(" +
            "u.nombre, u.apellido, u.email, u.telefono, a.genero, g.nombreGrado, g.especialidad, g.seccion) " +
            "FROM AlumnoModelo a " +
            "JOIN a.usuario u " +
            "JOIN a.grado g " +
            "WHERE a.codigoAlumno = :codigoAlumno")
    AlumnoDTO buscarAlumnoPorNombre(@Param("nombreAlumno") String nombreAlumno);

    @Query("SELECT new com.educacion.inedcuchilla.DTO.AlumnoDTO(" +
            "u.nombre, u.apellido, u.email, u.telefono, a.genero, g.nombreGrado, g.especialidad, g.seccion) " +
            "FROM AlumnoModelo a " +
            "JOIN a.usuario u " +
            "JOIN a.grado g")
    List<AlumnoDTO> listarAlumnos();


}
