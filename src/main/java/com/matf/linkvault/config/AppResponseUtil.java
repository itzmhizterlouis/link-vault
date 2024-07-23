package com.matf.linkvault.config;


import com.matf.linkvault.models.responses.AppResponse;

public class AppResponseUtil {
    public static AppResponse buildErrorResponse(String message) {
        return new AppResponse(false, message);
    }
}
