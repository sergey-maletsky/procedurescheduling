package com.procedure.scheduling.repository;

import com.procedure.scheduling.model.Doctor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Nullable
    Doctor findByName(@NotNull String name);
}
