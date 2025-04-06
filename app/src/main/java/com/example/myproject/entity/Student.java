package com.example.myproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "student")
@Getter
@Setter
public class Student {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "login_id")
    private String login_id;

    @ManyToOne
    @JoinColumn(name = "facilitator_id")
    private Facilitator facilitator;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;
}