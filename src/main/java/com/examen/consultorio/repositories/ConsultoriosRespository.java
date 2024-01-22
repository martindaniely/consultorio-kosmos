package com.examen.consultorio.repositories;


import com.examen.consultorio.entitys.ConsultoriosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultoriosRespository extends JpaRepository<ConsultoriosEntity, Long> {
}
