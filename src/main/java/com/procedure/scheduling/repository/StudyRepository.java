package com.procedure.scheduling.repository;

import com.procedure.scheduling.model.Study;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyRepository extends CrudRepository<Study, Long> {
}
