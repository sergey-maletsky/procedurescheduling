package com.procedure.scheduling.controller;

import com.procedure.scheduling.dto.JsonResult;
import com.procedure.scheduling.dto.PatientDto;
import com.procedure.scheduling.model.Gender;
import com.procedure.scheduling.service.PatientService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.procedure.scheduling.dto.JsonResult.ErrorCode.NO_ERROR;

@Controller
@RequestMapping("/patients")
public class PatientController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientService patientService;

    @ApiOperation("Get a patient by id")
    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> get(@PathVariable("id") Long id) {

        PatientDto existingPatient = patientService.findOne(id);
        if (Objects.isNull(existingPatient)) {
            log.error("Patient with id = {} not found", id);
            throw new EntityNotFoundException("Patient with id " + id + " not found");
        }
        return ResponseEntity.ok(existingPatient);
    }

    @ApiOperation("Get patients")
    @GetMapping
    public ResponseEntity<List<PatientDto>> list() {

        return ResponseEntity.ok(patientService.list());
    }

    @ApiOperation("Show the adding patient page")
    @GetMapping("/add")
    public String create(Model model) {

        model.addAttribute("genders", Gender.values());
        return "patient";
    }

    @ApiOperation("Create a new patient")
    @PostMapping
    public ResponseEntity<JsonResult> create(@Valid PatientDto patient, BindingResult bindingResult) {

        JsonResult result;
        ResponseEntity<JsonResult> responseEntity;
        if (bindingResult.hasErrors()) {
            result = setValidationErrors(bindingResult);
            responseEntity = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else {
            PatientDto newPatient = patientService.save(patient);
            result = new JsonResult<>(NO_ERROR);
            result.setResult(newPatient);
            responseEntity = new ResponseEntity<>(result, HttpStatus.OK);
        }

        return responseEntity;
    }

    @ApiOperation("Get genders")
    @GetMapping("/genders")
    public List<String> genderList() {
        return Stream.of(Gender.values())
                .map(Gender::name)
                .collect(Collectors.toList());
    }
}