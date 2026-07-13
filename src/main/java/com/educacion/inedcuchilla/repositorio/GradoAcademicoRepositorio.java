package com.educacion.inedcuchilla.repositorio;

import com.educacion.inedcuchilla.modelo.GradoAcademicoModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradoAcademicoRepositorio extends JpaRepository<GradoAcademicoModelo, Integer> {
}
