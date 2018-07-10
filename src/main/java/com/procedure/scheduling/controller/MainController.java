package com.procedure.scheduling.controller;

import com.procedure.scheduling.dto.PatientDto;
import com.procedure.scheduling.dto.StudyDto;
import com.procedure.scheduling.model.Gender;
import com.procedure.scheduling.model.StudyStatus;
import com.procedure.scheduling.service.PatientService;
import com.procedure.scheduling.service.StudyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/")
public class MainController {

    @Autowired
    private StudyService studyService;

    @Autowired
    private PatientService patientService;

    @ApiOperation("Show the main page")
    @GetMapping
    public ModelAndView index(ModelAndView modelAndView) {

        List<StudyDto> studies = studyService.list();
        List<PatientDto> patients = patientService.list();
        modelAndView.addObject("genders", Gender.values());
        modelAndView.addObject("studies", studies);
        modelAndView.addObject("patients", patients);
        modelAndView.addObject("studyStatuses", StudyStatus.values());
        modelAndView.setViewName("index");

        return modelAndView;
    }
}
