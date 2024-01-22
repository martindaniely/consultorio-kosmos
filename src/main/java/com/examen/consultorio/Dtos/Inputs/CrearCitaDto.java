package com.examen.consultorio.Dtos.Inputs;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;


@Getter
@Setter
public class CrearCitaDto {

    private String nombrePaciente;

    private LocalDateTime horaInicio;

    private LocalDateTime horaFin;

    private Long doctorId;

    private Long consultorioId;
}
