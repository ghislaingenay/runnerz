package com.running.runnerz.run;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RunNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public RunNotFoundException(String message) {
        super(message);
    }
    public RunNotFoundException() {
        super("Run not found");
    }

}
