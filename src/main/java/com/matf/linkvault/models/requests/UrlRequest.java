package com.matf.linkvault.models.requests;


import lombok.Data;

import java.util.List;

@Data
public class UrlRequest {

    private String folderName;
    private String name;
    private String url;
    private List<String> tags;
}
