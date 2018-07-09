package com.procedure.scheduling.service.impl;

import com.procedure.scheduling.dto.PatientDto;
import com.procedure.scheduling.dto.converter.BaseConverter;
import com.procedure.scheduling.exception.NotUniqueException;
import com.procedure.scheduling.model.Patient;
import com.procedure.scheduling.repository.PatientRepository;
import com.procedure.scheduling.service.PatientService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    private static final Logger log = LoggerFactory.getLogger(PatientServiceImpl.class);

    @Autowired
    private PatientRepository repository;

    @Autowired
    private BaseConverter converter;

    @Override
    public PatientDto findOne(@NotNull Long id) {

        Patient patient = repository.findById(id).get();
        return converter.convert(patient, PatientDto.class);
    }

    @Override
    public List<PatientDto> list() {

        return converter.convertList(repository.findAll(), PatientDto.class);
    }

    @Override
    public PatientDto save(@NotNull final PatientDto patientDto) {

        Patient existingPatient = repository.findByName(patientDto.getName());
        if(Objects.nonNull(existingPatient)) {
            log.error("Patient with name {} already exists", patientDto.getName());
            throw new NotUniqueException("Patient with name " + patientDto.getName() + " already exists");
        }

        Patient patient = converter.convert(patientDto, Patient.class);
        return converter.convert(repository.save(patient), PatientDto.class);
    }
}