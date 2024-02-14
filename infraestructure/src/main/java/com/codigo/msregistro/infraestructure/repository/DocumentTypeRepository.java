package com.codigo.msregistro.infraestructure.repository;

import com.codigo.msregistro.infraestructure.entity.DocumentTypeEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


public interface DocumentTypeRepository extends JpaRepository<DocumentTypeEntity,Long> {
    DocumentTypeEntity findByCodTipo(@Param("codTipo") String codTipo);
}
