package com.procedure.scheduling.service.impl;

import com.procedure.scheduling.dto.DoctorDto;
import com.procedure.scheduling.dto.converter.BaseConverter;
import com.procedure.scheduling.model.Doctor;
import com.procedure.scheduling.repository.DoctorRepository;
import com.procedure.scheduling.service.DoctorService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository repository;

    @Autowired
    private BaseConverter converter;

    @Override
    @Nullable
    public DoctorDto findOne(@NotNull Long id) {

        Doctor doctor = repository.findById(id).orElse(null);
        return converter.convert(doctor, DoctorDto.class);
    }

    @Override
    public List<DoctorDto> list() {

        return converter.convertList(repository.findAll(), DoctorDto.class);
    }
}