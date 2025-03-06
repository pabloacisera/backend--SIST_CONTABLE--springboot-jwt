package com.sistema_contable.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class InvoiceUtil {
    public InvoiceUtil() {
    }

    public static ResponseEntity<String> getResponseEntity(String message, HttpStatus httpStatus)
    {
        return new ResponseEntity<String>("message: " + message, httpStatus);
    }
}
