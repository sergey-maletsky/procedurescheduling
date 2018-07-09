package com.procedure.scheduling.repository;

import com.procedure.scheduling.model.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StudyRepository extends JpaRepository<Study, Long> {

    @Query(nativeQuery = true, value =
            "SELECT study.* "
                    + " FROM study "
                    + " WHERE ((study.start_time BETWEEN :from AND :to) "
                    + " OR (study.end_time BETWEEN :from AND :to)"
                    + " OR (study.start_time <= :from AND :to <= study.end_time)) "
                    + " AND (study.doctor_id = :doctorId OR study.room_id = :roomId OR study.patient_id = :patientId) "
                    + " AND study.status != 'FINISHED' ")
    List<Study> findAllActive(@Param("from") Date from, @Param("to") Date to,
                              @Param("doctorId") Long doctorId, @Param("roomId") Long roomId, @Param("patientId") Long patientId);
}
