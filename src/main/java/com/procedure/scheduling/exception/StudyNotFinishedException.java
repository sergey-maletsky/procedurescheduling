package com.procedure.scheduling.exception;

public class StudyNotFinishedException extends RuntimeException {

    public StudyNotFinishedException() {

    }

    public StudyNotFinishedException(String message) {

        super(message);
    }
}
