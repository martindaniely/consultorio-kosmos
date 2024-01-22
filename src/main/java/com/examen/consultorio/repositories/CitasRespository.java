package com.examen.consultorio.repositories;

import com.examen.consultorio.entitys.CitasEntity;
import com.examen.consultorio.entitys.ConsultoriosEntity;
import com.examen.consultorio.entitys.DoctoresEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CitasRespository extends JpaRepository<CitasEntity, Long> {


    // Consulta para verificar si existe dos horas de diferencia
    Optional<CitasEntity> findFirstByNombrePacienteAndHoraFinLessThanEqualOrderByHoraFinDesc(
            String nombrePaciente, LocalDateTime horaInicio);


    // Consulta para verificar si ya existe una cita en el mismo consultorio y hora.
    @Query("SELECT COUNT(c) > 0 FROM CitasEntity c WHERE c.consultorio.id = :consultorio AND ((c.horaInicio BETWEEN :horaInicio AND :horaFin) OR (c.horaFin BETWEEN :horaInicio AND :horaFin))")
    boolean existsByConsultorioAndHoraBetween(@Param("consultorio") Long consultorioId, @Param("horaInicio") LocalDateTime horaInicio, @Param("horaFin") LocalDateTime horaFin);

    // Consulta para verificar si ya existe una cita para el mismo doctor y hora.
    @Query("SELECT COUNT(c) > 0 FROM CitasEntity c WHERE c.doctor.id = :doctor AND ((c.horaInicio BETWEEN :horaInicio AND :horaFin) OR (c.horaFin BETWEEN :horaInicio AND :horaFin))")
    boolean existsByDoctorAndHoraBetween(@Param("doctor") Long doctorId, @Param("horaInicio") LocalDateTime horaInicio, @Param("horaFin") LocalDateTime horaFin);

    // Consulta para verificar si el doctor ya tiene 8 citas en el día.
    @Query("SELECT COUNT(c) >= 8 FROM CitasEntity c WHERE c.doctor.id = :doctor AND c.horaInicio BETWEEN :inicioDia AND :finDia")
    boolean excedeLimiteDeCitasParaElDoctor(@Param("doctor") Long doctorId, @Param("inicioDia") LocalDateTime inicioDia, @Param("finDia") LocalDateTime finDia);
}
