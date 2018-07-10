package com.procedure.scheduling.repository;

import com.procedure.scheduling.ProcedureSchedulingStarter;
import com.procedure.scheduling.model.Doctor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProcedureSchedulingStarter.class)
public class DoctorRepositoryTest {

    @MockBean
    DoctorRepository doctorRepository;

    Doctor doctor;

    @Before
    public void setUp() {
        Doctor doc = new Doctor();
        doc.setName("doctor one");
        this.doctor = doc;

        Mockito.when(doctorRepository.findByName(doc.getName()))
                .thenReturn(doc);

        Mockito.when(doctorRepository.save(doc)).thenReturn(doc);
    }

    @Test
    public void findDoctorByName() {
        String name = "doctor one";
        Doctor found = doctorRepository.findByName(name);

        assertThat(found.getName())
                .isEqualTo(name);
    }

    @Test
    public void saveDoctor() {
        Doctor found = doctorRepository.save(doctor);

        assertThat(found.getName())
                .isEqualTo(doctor.getName());
    }
}
