package com.matf.linkvault.models.responses;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterResponse {

    private String firstName;
    private String email;
    private String token;
}
