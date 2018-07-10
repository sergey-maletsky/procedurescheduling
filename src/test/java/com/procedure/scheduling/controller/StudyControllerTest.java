package com.procedure.scheduling.controller;

import com.procedure.scheduling.BaseRestApiTest;
import com.procedure.scheduling.dto.StudyDto;
import com.procedure.scheduling.service.StudyService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StudyControllerTest extends BaseRestApiTest {

    @Autowired
    StudyController studyController;

    @Test
    public void testGetStudyDto() throws Exception {
        Long id = 1L;
        String description = "new description";
        String roomName = "ROOM 1";
        String patientName = "patient 1";
        String doctorName = "doctor 1";

        StudyDto studyDto = new StudyDto();
        studyDto.setId(id);
        studyDto.setDescription(description);
        studyDto.setRoomName(roomName);
        studyDto.setPatientName(patientName);
        studyDto.setDoctorName(doctorName);

        StudyService studyService = Mockito.mock(StudyService.class);
        studyController.setStudyService(studyService);

        Mockito.when(studyService.findOne(id)).thenReturn(studyDto);

        mockMvc.perform(get("/studies/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.description", is(description)))
                .andExpect(jsonPath("$.roomName", is(roomName)))
                .andExpect(jsonPath("$.patientName", is(patientName)))
                .andExpect(jsonPath("$.doctorName", is(doctorName)));
    }

    @Test
    public void studyNotFound() throws Exception {
        Long id = 11L;
        mockMvc.perform(get("/studies/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllStudyDtos() throws Exception {

        List<StudyDto> studyDtos = new ArrayList<>();

        Long mainId = 1L;
        Long anotherId = 2L;
        String mainDescription = "new description";
        String anotherDescription = "another description";

        StudyDto studyDto = new StudyDto();
        studyDto.setId(mainId);
        studyDto.setDescription(mainDescription);

        StudyDto anotherStudyDto = new StudyDto();
        anotherStudyDto.setId(anotherId);
        anotherStudyDto.setDescription(anotherDescription);

        studyDtos.add(studyDto);
        studyDtos.add(anotherStudyDto);

        StudyService studyService = Mockito.mock(StudyService.class);
        studyController.setStudyService(studyService);

        Mockito.when(studyService.list()).thenReturn(studyDtos);

        mockMvc.perform(get("/studies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].description", is(mainDescription)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].description", is(anotherDescription)));
    }
}
