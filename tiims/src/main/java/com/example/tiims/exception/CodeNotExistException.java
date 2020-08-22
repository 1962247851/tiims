package com.example.tiims.exception;


import org.springframework.security.core.AuthenticationException;

public class CodeNotExistException extends AuthenticationException {

    public CodeNotExistException(String msg, Throwable t) {
        super(msg, t);
    }

    public CodeNotExistException(String msg) {
        super(msg);
    }
}