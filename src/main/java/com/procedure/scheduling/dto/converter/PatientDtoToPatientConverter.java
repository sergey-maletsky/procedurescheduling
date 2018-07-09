package com.procedure.scheduling.dto.converter;

import com.procedure.scheduling.dto.PatientDto;
import com.procedure.scheduling.model.Patient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PatientDtoToPatientConverter implements Converter<PatientDto, Patient> {

    @Override
    public Patient convert(PatientDto dto) {

        Patient patient = new Patient();
        patient.setName(dto.getName());
        patient.setSex(dto.getGender());
        patient.setDateOfBirth(dto.getDateOfBirth());

        return patient;
    }
}
