package com.matf.linkvault.models.responses;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private String firstName;
    private String email;
    private int userId;
}
