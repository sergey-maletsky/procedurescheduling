package com.procedure.scheduling.dto.converter;

import com.procedure.scheduling.dto.DoctorDto;
import com.procedure.scheduling.model.Doctor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DoctorToDoctorDtoConverter implements Converter<Doctor, DoctorDto> {

    @Override
    public DoctorDto convert(Doctor doctor) {

        DoctorDto dto = new DoctorDto();
        dto.setName(doctor.getName());

        return dto;
    }
}
