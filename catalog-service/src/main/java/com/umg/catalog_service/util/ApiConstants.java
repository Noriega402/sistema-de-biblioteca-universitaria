package com.umg.catalog_service.util;

import java.time.Instant;
import java.util.Map;

public class ApiConstants {

    public Instant timestamp = Instant.now();
    public int status;
    public String error;
    public String message;
    public String path;
    public Map<String, Object> details;

    public ApiConstants(int status, String error, String message, String path, Map<String, Object> details) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.details = details;
    }
}
