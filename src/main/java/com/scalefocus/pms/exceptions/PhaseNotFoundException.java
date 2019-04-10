// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PhaseNotFoundException extends RuntimeException {
    public PhaseNotFoundException(String msg) {
        super(msg);
    }

    public PhaseNotFoundException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
