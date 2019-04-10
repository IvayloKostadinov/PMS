// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.exceptions;

import org.springframework.security.core.AuthenticationException;

public class UserDisabledException extends AuthenticationException {
    public UserDisabledException(String msg) {
        super(msg);
    }

    public UserDisabledException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
