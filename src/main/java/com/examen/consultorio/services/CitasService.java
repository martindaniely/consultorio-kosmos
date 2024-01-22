package com.examen.consultorio.services;


import com.examen.consultorio.Dtos.Inputs.CrearCitaDto;
import com.examen.consultorio.entitys.CitasEntity;
import com.examen.consultorio.entitys.ConsultoriosEntity;
import com.examen.consultorio.entitys.DoctoresEntity;
import com.examen.consultorio.repositories.CitasRespository;
import com.examen.consultorio.repositories.ConsultoriosRespository;
import com.examen.consultorio.repositories.DoctoresRepository;
import com.examen.consultorio.services.impl.CitasServiceInterface;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CitasService implements CitasServiceInterface {

    @Autowired
    private CitasRespository citasRespository;

    @Autowired
    private DoctoresRepository doctoresRepository;

    @Autowired
    private ConsultoriosRespository consultoriosRespository;

    @Override
    public CitasEntity crearCita(CrearCitaDto dto) throws BadRequestException {
        validarDispConsultorio(dto);
        validarDispDoctor(dto);
        validaNumeroCitasDoctor(dto);
        return citasRespository.save(parseDtoToEntity(dto));
    }

    private void validarDispConsultorio(CrearCitaDto citaDto) throws BadRequestException {
        if (citasRespository.existsByConsultorioAndHoraBetween(
                citaDto.getConsultorioId(),
                citaDto.getHoraInicio(),
                citaDto.getHoraFin())) {
            throw new BadRequestException("No se puede agendar cita en el mismo consultorio a la misma hora");
        }
    }

    private void validarDispDoctor(CrearCitaDto citaDto) throws BadRequestException {
        if (citasRespository.existsByDoctorAndHoraBetween(
                citaDto.getDoctorId(),
                citaDto.getHoraInicio(),
                citaDto.getHoraFin())) {
            throw new BadRequestException("No se puede agendar cita con el mismo doctor a la misma hora");
        }
    }

    private void validarDosHoras(CrearCitaDto citaDto) throws BadRequestException {
        Optional<CitasEntity> cita = citasRespository.findFirstByNombrePacienteAndHoraFinLessThanEqualOrderByHoraFinDesc(citaDto.getNombrePaciente(), citaDto.getHoraInicio());
        if(!cita.isEmpty()) {
            if (cita.get().getHoraFin().getDayOfYear() == citaDto.getHoraFin().getDayOfYear()) {
                if ((citaDto.getHoraInicio().getHour() - cita.get().getHoraFin().getHour()) < 2) {
                    throw new BadRequestException("No se puede agendar cita con el mismo doctor a la misma hora");
                }
            }
        }
    }

    private void validaNumeroCitasDoctor(CrearCitaDto citaDto) throws BadRequestException {
        LocalDateTime fecha = LocalDateTime.parse(citaDto.getHoraInicio().toString());

        LocalDateTime inicioDelDia = fecha.toLocalDate().atStartOfDay();
        LocalDateTime finDelDia = inicioDelDia.plusDays(1).minusNanos(1);
        if (citasRespository.excedeLimiteDeCitasParaElDoctor(
                citaDto.getDoctorId(),
                inicioDelDia,
                finDelDia)) {
            throw new BadRequestException("No se puede agendar cita el doctor tiene el maximo de citas disponibles");
        }
    }

    private CitasEntity parseDtoToEntity(CrearCitaDto citaDto) {
        DoctoresEntity doctoresEntity = doctoresRepository.findById(citaDto.getDoctorId()).get();
        ConsultoriosEntity consultoriosEntity = consultoriosRespository.findById(citaDto.getConsultorioId()).get();
        return CitasEntity.builder()
                .doctor(doctoresEntity)
                .consultorio(consultoriosEntity)
                .horaInicio(citaDto.getHoraInicio())
                .horaFin(citaDto.getHoraFin())
                .nombrePaciente(citaDto.getNombrePaciente())
                .build();
    }
}
