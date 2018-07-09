package com.procedure.scheduling.dto.converter;

import com.procedure.scheduling.dto.PatientDto;
import com.procedure.scheduling.model.Patient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PatientToPatientDtoConverter implements Converter<Patient, PatientDto> {

    @Override
    public PatientDto convert(Patient patient) {

        PatientDto dto = new PatientDto();
        dto.setName(patient.getName());
        dto.setGender(patient.getSex());
        dto.setDateOfBirth(patient.getDateOfBirth());

        return dto;
    }
}
