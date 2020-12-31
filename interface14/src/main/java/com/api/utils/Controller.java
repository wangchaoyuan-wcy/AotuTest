package com.api.utils;

import com.api.config.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;

public class Controller {
    @Autowired
    private HttpClient httpClient;
    public HttpClient getHttpClient() {
        return httpClient;
    }







}
