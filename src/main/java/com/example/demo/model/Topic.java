package com.example.demo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "Todo")
public class Topic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(name = "Topic_Name", unique = true, nullable = false)
    private String name;

    @Column(name = "date_creation")
    @Temporal(TemporalType.DATE)
    private Date creation;
    // jointure
    @OneToMany(mappedBy = "topic")
    private Collection<Task> tasks;

    public Topic() {
        tasks = new ArrayList<>();
    }


    


}
