package com.examen.consultorio.repositories;

import com.examen.consultorio.entitys.DoctoresEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctoresRepository extends JpaRepository<DoctoresEntity, Long> {
}
