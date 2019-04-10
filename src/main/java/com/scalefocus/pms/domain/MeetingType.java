// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.domain;

public enum MeetingType {

    CALL("Call"),
    DISCUSSION("Discussion");

    private final String displayName;

    MeetingType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
