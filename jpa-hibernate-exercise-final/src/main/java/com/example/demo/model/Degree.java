package com.example.demo.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Degree {

    @Id
    private UUID degreeId;

    private String title;

    public Degree() {
        this.degreeId = UUID.randomUUID();
    }

    public Degree(String title) {
        this();
        this.title = title;
    }

    public UUID getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(UUID degreeId) {
        this.degreeId = degreeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}