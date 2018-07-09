package com.procedure.scheduling.dto.converter;

import com.procedure.scheduling.dto.StudyDto;
import com.procedure.scheduling.model.Study;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StudyToStudyDtoConverter implements Converter<Study, StudyDto> {

    @Override
    public StudyDto convert(Study study) {

        StudyDto dto = new StudyDto();

        dto.setId(study.getId());
        dto.setDescription(study.getDescription());
        dto.setStatus(study.getStatus());
        dto.setStartTime(study.getStartTime());
        dto.setEndTime(study.getEndTime());
        dto.setDoctorName(study.getDoctor().getName());
        dto.setPatientName(study.getPatient().getName());
        dto.setRoomName(study.getRoom().getName());

        return dto;
    }
}
