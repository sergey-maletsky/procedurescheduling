package com.procedure.scheduling.dto;

import com.procedure.scheduling.model.Gender;
import org.jetbrains.annotations.Nullable;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class PatientDto {

    @NotBlank(message = "Can not be empty")
    private String name;

    @Nullable
    private Gender gender;

    @Nullable
    private Date dateOfBirth;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
