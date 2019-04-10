// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MeetingNotFoundException extends RuntimeException {

    public MeetingNotFoundException() {
        super();
    }

    public MeetingNotFoundException(String message) {
        super(message);
    }

    public MeetingNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
