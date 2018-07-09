package com.procedure.scheduling.dto;

import javax.validation.constraints.NotBlank;

public class RoomDto {

    @NotBlank(message = "Can not be empty")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
