package com.examen.consultorio.services.impl;

import com.examen.consultorio.Dtos.Inputs.CrearCitaDto;
import com.examen.consultorio.entitys.CitasEntity;
import org.apache.coyote.BadRequestException;

public interface CitasServiceInterface {

    CitasEntity crearCita(CrearCitaDto dto) throws BadRequestException;
}
