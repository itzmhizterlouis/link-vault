package com.matf.linkvault.models.responses;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenericResponse {

    private String message;
}
