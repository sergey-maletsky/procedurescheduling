package com.procedure.scheduling.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "study")
public class Study {

    public Study() {

    }

    @Id
    @SequenceGenerator(name = "study_sequence", sequenceName = "study_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "study_sequence")
    private Long id;

    @Column
    private String description;
}
