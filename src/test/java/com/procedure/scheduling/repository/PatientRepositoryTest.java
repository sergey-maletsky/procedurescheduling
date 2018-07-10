package com.procedure.scheduling.repository;

import com.procedure.scheduling.ProcedureSchedulingStarter;
import com.procedure.scheduling.model.Gender;
import com.procedure.scheduling.model.Patient;
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
public class PatientRepositoryTest {

    @MockBean
    PatientRepository patientRepository;

    Patient patient;

    @Before
    public void setUp() {
        Patient alex = new Patient();
        alex.setName("Alex");
        alex.setSex(Gender.male);
        this.patient = alex;

        Mockito.when(patientRepository.findByName(alex.getName()))
                .thenReturn(alex);

        Mockito.when(patientRepository.save(alex)).thenReturn(alex);
    }

    @Test
    public void findPatientByName() {
        String name = "Alex";
        Patient found = patientRepository.findByName(name);

        assertThat(found.getName())
                .isEqualTo(name);
    }

    @Test
    public void savePatient() {
        Patient found = patientRepository.save(patient);

        assertThat(found.getName())
                .isEqualTo(patient.getName());
    }
}


