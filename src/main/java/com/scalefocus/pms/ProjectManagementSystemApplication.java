// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SuppressWarnings("PMD.UseUtilityClass")
public class ProjectManagementSystemApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectManagementSystemApplication.class);

    /**
     * PMS main method.
     *
     * @param args - PMS - starting from this method
     */
    public static void main(String[] args) {

        LOGGER.warn("Application started");
        SpringApplication.run(ProjectManagementSystemApplication.class, args);

    }
}
