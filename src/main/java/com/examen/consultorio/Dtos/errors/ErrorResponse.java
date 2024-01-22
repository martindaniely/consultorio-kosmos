package com.examen.consultorio.Dtos.errors;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

    private int status;
    private String error;
    private String message;
}
