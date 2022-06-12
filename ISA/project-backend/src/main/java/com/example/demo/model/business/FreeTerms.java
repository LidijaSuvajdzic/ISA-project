package com.example.demo.model.business;

import com.example.demo.model.users.Client;
import com.example.demo.model.users.Instructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name="freeTerms")
public class FreeTerms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = true)
    private LocalDateTime freeFrom;
    @Column(nullable = true)
    private LocalDateTime freeTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    @JsonIgnoreProperties("freeTerms")
    private Instructor instructor;

    public FreeTerms() {
    }

    public FreeTerms(int id, LocalDateTime freeFrom, LocalDateTime freeTo, Instructor instructor) {
        this.id = id;
        this.freeFrom = freeFrom;
        this.freeTo = freeTo;
        this.instructor = instructor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getFreeFrom() {
        return freeFrom;
    }

    public void setFreeFrom(LocalDateTime freeFrom) {
        this.freeFrom = freeFrom;
    }

    public LocalDateTime getFreeTo() {
        return freeTo;
    }

    public void setFreeTo(LocalDateTime freeTo) {
        this.freeTo = freeTo;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
}
