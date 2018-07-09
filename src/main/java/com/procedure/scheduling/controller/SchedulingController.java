package com.procedure.scheduling.controller;

import com.procedure.scheduling.dto.PatientDto;
import com.procedure.scheduling.dto.StudyDto;
import com.procedure.scheduling.model.Gender;
import com.procedure.scheduling.model.StudyStatus;
import com.procedure.scheduling.service.PatientService;
import com.procedure.scheduling.service.StudyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class SchedulingController {

    @Autowired
    private StudyService studyService;

    @Autowired
    private PatientService patientService;

    @ApiOperation("Show the main page")
    @GetMapping
    public String index(Model model) {

        List<StudyDto> studies = studyService.list();
        List<PatientDto> patients = patientService.list();
        model.addAttribute("genders", Gender.values());
        model.addAttribute("studies", studies);
        model.addAttribute("patients", patients);
        model.addAttribute("studyStatuses", StudyStatus.values());

        return "index";
    }
}
