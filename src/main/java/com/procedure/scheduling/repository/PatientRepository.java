package com.procedure.scheduling.repository;

import com.procedure.scheduling.model.Patient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Nullable
    Patient findByName(@NotNull String name);
}
