package com.example.demo.model;

import jakarta.persistence.*;
import java.util.UUID;
import java.util.Date;

@Entity
public class Candidate {

    @Id
    private UUID candidateId;

    private String lastName;
    private String firstName;
    private String email;

    @Temporal(TemporalType.DATE)
    private Date applicationDate;

    private String remarks;

    @ManyToOne
    private Degree highestDegree;

    private boolean accepted;

    public Candidate() {
        this.candidateId = UUID.randomUUID();
    }

    public UUID getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(UUID candidateId) {
        this.candidateId = candidateId;
    }

    // Other getters and setters omitted for brevity
}