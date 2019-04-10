// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.constants;

public final class ValidationConstants {

    public static final int USERNAME_MIN_LENGTH = 3;
    public static final int USERNAME_MAX_LENGTH = 45;
    public static final int EMAIL_MIN_LENGTH = 3;
    public static final int EMAIL_MAX_LENGTH = 45;
    public static final int PASSWORD_MIN_LENGTH = 5;
    public static final int NAME_MAX_LENGTH = 30;
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String USER_NOT_FOUND = "No such user.";
    public static final String TEAM_NOT_FOUND = "No such team.";
    public static final String USER_IS_DISABLED = "This User has been disabled : ";
    public static final String TASK_NOT_FOUND = "No such task.";
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm";
    public static final String PROJECT_NOT_FOUND = "The project could not be found";
    public static final String PHASE_NOT_FOUND = "The phase could not be found";
    public static final String INVALID_INPUT = " is not a valid input";

    private ValidationConstants() {
    }
}
