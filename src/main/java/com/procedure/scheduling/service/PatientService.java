package com.procedure.scheduling.service;

import com.procedure.scheduling.dto.PatientDto;

import java.util.List;

public interface PatientService {

    PatientDto findOne(Long id);

    List<PatientDto> list();

    PatientDto save(PatientDto patient);
}