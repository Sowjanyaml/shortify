package com.example.shortify.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "URL Not Found")
public class UrlNotFoundException extends Exception {
}
