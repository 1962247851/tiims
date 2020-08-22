package com.example.tiims.exception;

import org.springframework.security.core.AuthenticationException;

public class NullUserNameException extends AuthenticationException {

    public NullUserNameException(String msg, Throwable t) {
        super(msg, t);
    }

    public NullUserNameException(String msg) {
        super(msg);
    }
}
