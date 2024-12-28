package com.example.bookmyshowapp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.GeneratorStrategy;

import java.util.Date;

@Getter
@Setter
@Entity
@MappedSuperclass()
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //AUTO-INCREMENT
    private Long id;
    private Date createdAt;
    private Date lastModifiedAt;
}
//Don't want to create table for BaseModel but want all child tables to have all its attributes
