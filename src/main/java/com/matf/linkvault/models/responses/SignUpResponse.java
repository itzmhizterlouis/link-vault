package com.matf.linkvault.models.responses;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpResponse {

    private String firstName;
    private int userId;
    private String email;
    private String token;
}
