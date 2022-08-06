package com.example.demo.dto;

import java.util.Date;

import com.example.demo.model.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    
    private String name;
    private String description;
    private Date deadline;
    private long topic;

    public TaskDTO(Task task) {
        this.name = task.getName();
        this.description = task.getDescription();
        this.deadline = task.getDeadline();
        this.topic = task.getTopic().getId();
    }

    public Task toTaskDTO() {
        Task task = new Task();
        task.setName(getName());
        task.setDescription(getDescription());
        task.setDeadline(getDeadline());
        return task;
    }
}
