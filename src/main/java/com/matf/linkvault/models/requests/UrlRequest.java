package com.matf.linkvault.models.requests;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Data
public class UrlRequest {

    private int folderId;
    private String name;
    private String url;
    private List<String> tags;
}
