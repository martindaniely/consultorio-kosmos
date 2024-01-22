package com.examen.consultorio.controllers;


import com.examen.consultorio.Dtos.Inputs.CrearCitaDto;
import com.examen.consultorio.Dtos.errors.ErrorResponse;
import com.examen.consultorio.entitys.CitasEntity;
import com.examen.consultorio.services.CitasService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/citas")
public class CitasController {

    @Autowired
    private CitasService citasService;

    @PostMapping("/save")
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cita guardada correctamente"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<?> crearCita(@RequestBody CrearCitaDto citaDto) {
        try {
            CitasEntity citaGuardada = citasService.crearCita(citaDto);
            return ResponseEntity.ok(citaGuardada);
        } catch (BadRequestException e) {
            ErrorResponse errorResponse = new ErrorResponse(400, "Bad Request", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
