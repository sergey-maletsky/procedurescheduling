package com.procedure.scheduling.repository;

import com.procedure.scheduling.ProcedureSchedulingStarter;
import com.procedure.scheduling.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProcedureSchedulingStarter.class)
public class StudyRepositoryTest {

    @MockBean
    StudyRepository studyRepository;

    Study study;

    @Before
    public void setUp() {
        List<Study> studies = new ArrayList<>();

        Study study = new Study();
        study.setId(1L);
        Room room = new Room();
        room.setId(1L);
        room.setName("ROOM 0");

        Patient alex = new Patient();
        alex.setId(1L);
        alex.setName("Alex");
        alex.setSex(Gender.male);

        Doctor doc = new Doctor();
        doc.setId(1L);
        doc.setName("doctor one");

        study.setDescription("new description");
        study.setRoom(room);
        study.setPatient(alex);
        study.setDoctor(doc);
        study.setStartTime(new Date(2018, 7, 5, 7, 12, 11));
        study.setEndTime(new Date(2018, 7, 5, 7, 50, 34));
        study.setStatus(StudyStatus.PLANNED);

        Study study1 = new Study();
        study1.setId(2L);
        Room room1 = new Room();
        room1.setId(2L);
        room1.setName("ROOM 1");

        Patient alex1 = new Patient();
        alex1.setId(2L);
        alex1.setName("Alex 1");
        alex1.setSex(Gender.male);

        Doctor doc1 = new Doctor();
        doc1.setId(2L);
        doc1.setName("doctor two");

        study1.setDescription("new description 1");
        study1.setRoom(room1);
        study1.setPatient(alex1);
        study1.setDoctor(doc1);
        study1.setStartTime(new Date(2018, 7, 5, 7, 20, 25));
        study1.setEndTime(new Date(2018, 7, 5, 8, 20, 25));
        study1.setStatus(StudyStatus.IN_PROGRESS);

        studies.add(study);
        studies.add(study1);

        Mockito.when(studyRepository.findAllActive(
                new Date(2018, 7, 5, 7, 15, 20),
                new Date(2018, 7, 5, 8, 10, 20),
                1L, 2L, 1L))
                .thenReturn(studies);

        List<Study> studies1 = new ArrayList<>();
        studies1.add(study1);
        Mockito.when(studyRepository.findAllActive(
                new Date(2018, 7, 5, 8, 15, 20),
                new Date(2018, 7, 5, 8, 50, 20),
                1L, 2L, 1L))
                .thenReturn(studies1);
    }

    @Test
    public void findAllActive() {
        String studyDescription = "new description";
        String studyDescription1 = "new description 1";
        List<Study> foundList = studyRepository.findAllActive(new Date(2018, 7, 5, 7, 15, 20),
                new Date(2018, 7, 5, 8, 10, 20),
                1L, 2L, 1L);

        assertThat(foundList.get(0).getDescription())
                .isEqualTo(studyDescription);

       List<Study> foundList1 = studyRepository.findAllActive(
                new Date(2018, 7, 5, 8, 15, 20),
                new Date(2018, 7, 5, 8, 50, 20),
                1L, 2L, 1L);

        assertThat(foundList1.get(0).getDescription())
                .isEqualTo(studyDescription1);
    }
}
