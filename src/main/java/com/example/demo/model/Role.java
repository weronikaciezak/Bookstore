package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}