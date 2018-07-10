package com.procedure.scheduling.service.impl;

import com.procedure.scheduling.dto.StudyDto;
import com.procedure.scheduling.dto.converter.BaseConverter;
import com.procedure.scheduling.exception.NotUniqueException;
import com.procedure.scheduling.model.*;
import com.procedure.scheduling.repository.DoctorRepository;
import com.procedure.scheduling.repository.PatientRepository;
import com.procedure.scheduling.repository.RoomRepository;
import com.procedure.scheduling.repository.StudyRepository;
import com.procedure.scheduling.service.StudyService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class StudyServiceImpl implements StudyService {

    private static final Logger log = LoggerFactory.getLogger(StudyServiceImpl.class);

    private final StudyRepository repository;
    private final BaseConverter converter;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public StudyServiceImpl(StudyRepository repository, BaseConverter converter,
                            DoctorRepository doctorRepository, PatientRepository patientRepository,
                            RoomRepository roomRepository) {

        this.repository = repository;
        this.converter = converter;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    @Nullable
    public StudyDto findOne(@NotNull Long id) {

        Study study = repository.findById(id).orElse(null);
        return converter.convert(study, StudyDto.class);
    }

    @Override
    public List<StudyDto> list() {

        return converter.convertList(repository.findAll(), StudyDto.class);
    }

    @NotNull
    @Override
    public StudyDto save(@NotNull StudyDto studyDto) {

        if (Objects.nonNull(studyDto.getId())) {
            return update(studyDto);
        } else {
            return saveNew(studyDto);
        }
    }

    private StudyDto saveNew(@NotNull StudyDto studyDto) {

        Study study = getStudyAndUpdate(studyDto);
        List<Study> studies = repository
                .findAllActive(study.getStartTime(), study.getEndTime(),
                study.getDoctor().getId(), study.getRoom().getId(),
                        study.getPatient().getId());

        if (!CollectionUtils.isEmpty(studies)) {
            log.error("Study(studies) with the same time is(are) going on");
            throw new NotUniqueException("Study(studies) with the same time is(are) going on");
        }

        return converter.convert(repository.save(study), StudyDto.class);
    }

    @NotNull
    private StudyDto update(@NotNull StudyDto studyDto) {

        StudyDto existingStudyDto = findOne(studyDto.getId());
        if (Objects.isNull(existingStudyDto)) {
            log.error("Study with id = {} not found", studyDto.getId());
            throw new EntityNotFoundException("Study with id " + studyDto.getId() + " not found");
        }

        Study study = getStudyAndUpdate(studyDto);
        return converter.convert(repository.save(study), StudyDto.class);
    }

    private Study getStudyAndUpdate(StudyDto studyDto) {

        if (studyDto.getStartTime().getTime() > studyDto.getEndTime().getTime()) {
            log.error("End time before start time");
            throw new IllegalArgumentException("End time before start time");
        }

        Study study = converter.convert(studyDto, Study.class);

        Doctor doctor = doctorRepository.findByName(studyDto.getDoctorName());
        Patient patient = patientRepository.findByName(studyDto.getPatientName());
        Room room = roomRepository.findByName(studyDto.getRoomName());
        study.setDoctor(doctor);
        study.setPatient(patient);
        study.setRoom(room);

        return study;
    }

    @Override
    public void checkStatusAndUpdateStudy(@NotNull StudyDto existingStudy, @NotNull StudyStatus newStatus) {

        StudyStatus existingStatus = existingStudy.getStatus();
        if (existingStatus.equals(StudyStatus.FINISHED) ||
                (existingStatus.equals(StudyStatus.IN_PROGRESS) && newStatus.equals(StudyStatus.PLANNED))) {
            log.error("Can not change the status from {} to {}", existingStatus, newStatus);
            throw new IllegalArgumentException("Can not change the status from " + existingStatus + " to " + newStatus);
        }
        if (newStatus.equals(StudyStatus.FINISHED)) {
            existingStudy.setEndTime(new Date());
        }

        existingStudy.setStatus(newStatus);
    }
}
