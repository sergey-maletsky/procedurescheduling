package com.procedure.scheduling.controller;

import com.procedure.scheduling.BaseRestApiTest;
import com.procedure.scheduling.dto.PatientDto;
import com.procedure.scheduling.model.Gender;
import com.procedure.scheduling.service.PatientService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PatientControllerTest extends BaseRestApiTest {

    @Autowired
    PatientController patientController;

    @Test
    public void testGetPatientDto() throws Exception {
        Long id = 1L;
        String patientName = "Julia Roberts";

        PatientDto patientDto = new PatientDto();
        patientDto.setName(patientName);
        patientDto.setGender(Gender.female);

        PatientService patientService = Mockito.mock(PatientService.class);
        patientController.setPatientService(patientService);

        Mockito.when(patientService.findOne(id)).thenReturn(patientDto);

        mockMvc.perform(get("/patients/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(patientName)))
                .andExpect(jsonPath("$.gender", is(Gender.female.name())));
    }

    @Test
    public void patientNotFound() throws Exception {
        Long id = 11L;
        mockMvc.perform(get("/patients/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllPatientDtos() throws Exception {

        List<PatientDto> patientDtos = new ArrayList<>();

        String mainPatientName = "Julia Roberts";
        String anotherPatientName = "Tom Soyer";

        PatientDto mainPatientDto = new PatientDto();
        mainPatientDto.setName(mainPatientName);
        mainPatientDto.setGender(Gender.female);

        PatientDto anotherPatientDto = new PatientDto();
        anotherPatientDto.setName(anotherPatientName);
        anotherPatientDto.setGender(Gender.male);

        patientDtos.add(mainPatientDto);
        patientDtos.add(anotherPatientDto);

        PatientService patientService = Mockito.mock(PatientService.class);
        patientController.setPatientService(patientService);

        Mockito.when(patientService.list()).thenReturn(patientDtos);

        mockMvc.perform(get("/patients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(mainPatientName)))
                .andExpect(jsonPath("$[0].gender", is(Gender.female.name())))
                .andExpect(jsonPath("$[1].name", is(anotherPatientName)))
                .andExpect(jsonPath("$[1].gender", is(Gender.male.name())));
    }
}
