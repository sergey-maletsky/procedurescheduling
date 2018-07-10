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
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.procedure.scheduling.dto.JsonResult.ErrorCode.NO_ERROR;

@RestController
@RequestMapping("/studies")
public class StudyController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(StudyController.class);

    private StudyService studyService;
    private DoctorService doctorService;
    private PatientService patientService;
    private RoomService roomService;

    @Autowired
    public void setStudyService(StudyService studyService) {
        this.studyService = studyService;
    }
    @Autowired
    public void setDoctorService(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
    @Autowired
    public void setPatientService(PatientService patientService) {
        this.patientService = patientService;
    }
    @Autowired
    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }

    @ApiOperation("Get a study by id")
    @GetMapping("/{id}")
    public ResponseEntity<StudyDto> get(@PathVariable("id") Long id) {

        StudyDto existingStudy = studyService.findOne(id);
        if (Objects.isNull(existingStudy)) {
            log.error("Study with id = {} not found", id);
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
    public ModelAndView edit(@PathVariable Long id, ModelAndView modelAndView) {

        StudyDto studyDto = studyService.findOne(id);
        modelAndView.addObject("study", studyDto);
        modelAndView.addObject("studyStatuses", StudyStatus.values());
        modelAndView.setViewName("study");

        return modelAndView;
    }

    @ApiOperation("Show the add study page")
    @GetMapping("/add")
    public ModelAndView create(ModelAndView modelAndView) {

        modelAndView.addObject("doctors", doctorService.list());
        modelAndView.addObject("rooms", roomService.list());
        modelAndView.addObject("patients", patientService.list());
        modelAndView.addObject("studyStatuses", StudyStatus.values());
        modelAndView.setViewName("scheduling");

        return modelAndView;
    }

    @ApiOperation("Create a new study")
    @PostMapping
    public ResponseEntity<JsonResult> create(@Valid StudyDto study, BindingResult bindingResult) {

        ResponseEntity<JsonResult> responseEntity =
                createResponse(study, bindingResult, studyDto -> studyService.save(studyDto));

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
            log.error("Study with id = {} not found", id);
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
