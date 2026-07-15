package com.educacion.inedcuchilla.repositorio;

import com.educacion.inedcuchilla.modelo.GradoAcademicoModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GradoAcademicoRepositorio extends JpaRepository<GradoAcademicoModelo, Integer> {
    Boolean existsGradoAcademicoModeloByEspecialidadIdEspecialidadAndGradoIdGradoAndSeccionIdSeccion(Integer idEspecialidad, Integer idGrado, Integer idSeccion);

    GradoAcademicoModelo findByIdGradoAcademico(Integer idGradoAcademico);
}
