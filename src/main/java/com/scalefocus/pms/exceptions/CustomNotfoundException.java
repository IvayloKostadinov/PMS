// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomNotfoundException extends RuntimeException {
    public CustomNotfoundException() {
        super();
    }

    public CustomNotfoundException(String message) {
        super(message);
    }

    public CustomNotfoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
