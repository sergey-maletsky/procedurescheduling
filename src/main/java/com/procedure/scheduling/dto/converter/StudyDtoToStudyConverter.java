package com.procedure.scheduling.dto.converter;

import com.procedure.scheduling.dto.StudyDto;
import com.procedure.scheduling.model.Study;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class StudyDtoToStudyConverter implements Converter<StudyDto, Study> {

    @Override
    public Study convert(StudyDto dto) {

        Study study = new Study();
        if (Objects.nonNull(dto.getId())) {
            study.setId(dto.getId());
        }
        study.setDescription(dto.getDescription());
        study.setStatus(dto.getStatus());
        study.setStartTime(dto.getStartTime());
        study.setEndTime(dto.getEndTime());

        return study;
    }
}
