package com.procedure.scheduling.service;

import com.procedure.scheduling.dto.StudyDto;
import com.procedure.scheduling.model.StudyStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public interface StudyService {

    @Nullable
    StudyDto findOne(@NotNull Long id);

    List<StudyDto> list();

    @NotNull
    StudyDto save(@NotNull StudyDto study);

    void checkStatusAndUpdateStudy(@NotNull StudyDto existingStudy, @NotNull StudyStatus newStatus);
}
