package com.examen.consultorio.entitys;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "citas")
@Getter
@Setter
@ToString
@Builder
public class CitasEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "citas_id_sequence")
    @SequenceGenerator(name = "citas_id_sequence", sequenceName = "citas_id_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "fecha_inicio")
    private LocalDateTime horaInicio;

    @Column(name = "fecha_fin")
    private LocalDateTime horaFin;

    @Column(name = "nombre_paciente")
    private String nombrePaciente;

    @ManyToOne(targetEntity = DoctoresEntity.class)
    private DoctoresEntity doctor;

    @ManyToOne(targetEntity = ConsultoriosEntity.class)
    private ConsultoriosEntity consultorio;
}
