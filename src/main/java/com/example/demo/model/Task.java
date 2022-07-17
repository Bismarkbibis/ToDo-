package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@NoArgsConstructor
@Entity
public class Task implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false,length = 255)
    private String name;
    @Column(nullable = true,length = 255)
    private String description;

    @JsonIgnore
    @Column(nullable = true)
    @Temporal(TemporalType.DATE)
    private Date deadline;

    //jointure
    @ManyToOne()
    private Topic topic;


    public Task(String name, String description, Date deadline, Topic topic) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.topic = topic;
    }
    
    
    
}
