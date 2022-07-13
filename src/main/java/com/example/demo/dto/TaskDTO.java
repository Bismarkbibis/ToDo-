package com.example.demo.dto;

import java.util.Date;

import lombok.Data;

@Data
public class TaskDTO {
    
    private String name;
    private String description;
    private Date deadline;
    private int topic;

    
}
