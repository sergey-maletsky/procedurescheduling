package com.procedure.scheduling.controller;

import com.procedure.scheduling.dto.JsonResult;
import com.procedure.scheduling.dto.StudyDto;
import com.procedure.scheduling.model.StudyStatus;
import com.procedure.scheduling.service.DoctorService;
import com.procedure.scheduling.service.PatientService;
import com.procedure.scheduling.service.RoomService;
import com.procedure.scheduling.service.StudyService;
import io.swagger.annotations.ApiOperation;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.procedure.scheduling.dto.JsonResult.ErrorCode.NO_ERROR;

@Controller
@RequestMapping("/studies")
public class StudyController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(StudyController.class);

    @Autowired
    private StudyService studyService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private RoomService roomService;

    @ApiOperation("Get a study by id")
    @GetMapping("/{id}")
    public ResponseEntity<StudyDto> get(@PathVariable("id") Long id) {

        StudyDto existingStudy = studyService.findOne(id);
        if (Objects.isNull(existingStudy)) {
            throw new EntityNotFoundException("Study with id " + id + " not found");
        }
        return ResponseEntity.ok(existingStudy);
    }

    @ApiOperation("Get studies")
    @GetMapping
    public ResponseEntity<List<StudyDto>> list() {

        return ResponseEntity.ok(studyService.list());
    }

    @ApiOperation("Show the edit study page")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        StudyDto studyDto = studyService.findOne(id);
        model.addAttribute("study", studyDto);
        model.addAttribute("studyStatuses", StudyStatus.values());

        return "study";
    }

    @ApiOperation("Show the add study page")
    @GetMapping("/add")
    public String create(Model model) {

        model.addAttribute("doctors", doctorService.list());
        model.addAttribute("rooms", roomService.list());
        model.addAttribute("patients", patientService.list());
        model.addAttribute("studyStatuses", StudyStatus.values());

        return "scheduling";
    }

    @ApiOperation("Create a new study")
    @PostMapping
    public ResponseEntity<JsonResult> create(@Valid StudyDto study, BindingResult bindingResult) {

        JsonResult result;
        ResponseEntity<JsonResult> responseEntity;
        if (bindingResult.hasErrors()) {
            result = setValidationErrors(bindingResult);
            responseEntity = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else {
            StudyDto newStudy = studyService.save(study);
            result = new JsonResult<>(NO_ERROR);
            result.setResult(newStudy);
            responseEntity = new ResponseEntity<>(result, HttpStatus.OK);
        }

        return responseEntity;
    }

    @ApiOperation("Update a study")
    @PutMapping("/{id}")
    public JsonResult<StudyDto> update(@Valid StudyDto studyDto, @PathVariable Long id, BindingResult bindingResult) {

        JsonResult result;
        if (bindingResult.hasErrors()) {
            result = setValidationErrors(bindingResult);
        } else {
            studyDto.setId(id);
            result = new JsonResult<>(NO_ERROR);
            result.setResult(studyService.save(studyDto));
        }

        return result;
    }

    @ApiOperation("Update a study status")
    @PutMapping("/status/{id}")
    public JsonResult<StudyDto> update(@PathVariable Long id, @NotNull StudyStatus status) {

        StudyDto existingStudy = studyService.findOne(id);
        if (Objects.isNull(existingStudy)) {
            throw new EntityNotFoundException("Study with id " + id + " not found");
        }

        studyService.checkStatusAndUpdateStudy(existingStudy, status);

        JsonResult result = new JsonResult<>(NO_ERROR);
        result.setResult(studyService.save(existingStudy));

        return result;
    }

    @ApiOperation("Get statuses")
    @GetMapping("/statuses")
    public List<String> statusList() {
        return Stream.of(StudyStatus.values())
                .map(StudyStatus::name)
                .collect(Collectors.toList());
    }
}