package com.procedure.scheduling.dto;

import com.procedure.scheduling.dto.validation.ValidDate;
import com.procedure.scheduling.model.StudyStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class StudyDto {

    @Nullable
    private Long id;

    @NotBlank(message = "Can not be empty")
    private String doctorName;

    @NotBlank(message = "Can not be empty")
    private String roomName;

    @NotBlank(message = "Can not be empty")
    private String patientName;

    @NotBlank(message = "Can not be empty")
    private String description;

    @NotNull(value = "Can not be null")
    private StudyStatus status;

    @ValidDate
    private Date startTime;

    @Nullable
    private Date endTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StudyStatus getStatus() {
        return status;
    }

    public void setStatus(StudyStatus status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
