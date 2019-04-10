// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(String msg) {
        super(msg);
    }

    public ProjectNotFoundException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
