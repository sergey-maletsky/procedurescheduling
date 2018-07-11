package com.procedure.scheduling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ProcedureSchedulingStarter extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ProcedureSchedulingStarter.class, args);
    }
}
