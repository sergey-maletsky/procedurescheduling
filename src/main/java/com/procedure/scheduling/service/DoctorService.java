package com.procedure.scheduling.service;

import com.procedure.scheduling.dto.DoctorDto;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface DoctorService {

    @Nullable DoctorDto findOne(@NotNull Long id);

    List<DoctorDto> list();
}
