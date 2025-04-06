package com.example.myproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;


import jakarta.persistence.Column;

@Entity
@Table(name = "facilitator")
@Getter
@Setter
public class Facilitator {
    @Id
    @Column(name = "id")
    private int id;
}
