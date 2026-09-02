package com.educacion.inedcuchilla.repositorio;

import com.educacion.inedcuchilla.Modelo.GradoAcademicoMateriaModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradoAcademicoMateriaRepositorio extends JpaRepository<GradoAcademicoMateriaModelo, Integer> {
}
