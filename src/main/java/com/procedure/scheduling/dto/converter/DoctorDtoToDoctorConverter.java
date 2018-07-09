package com.procedure.scheduling.dto.converter;

import com.procedure.scheduling.dto.DoctorDto;
import com.procedure.scheduling.model.Doctor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DoctorDtoToDoctorConverter implements Converter<DoctorDto, Doctor> {

    @Override
    public Doctor convert(DoctorDto dto) {

        Doctor doctor = new Doctor();
        doctor.setName(dto.getName());

        return doctor;
    }
}
