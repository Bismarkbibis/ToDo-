package com.example.demo.controler;

import java.util.List;
import java.util.Optional;

import com.example.demo.exception.CustomeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.example.demo.dto.TaskDTO;
import com.example.demo.model.Task;
import com.example.demo.service.TaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
@RequestMapping("/task")
@CrossOrigin("*")
public class TaskController {
    
    @Autowired
    private TaskService taskService;

    private Task task =null;


    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task>listTask= taskService.getAllTask();
        return ResponseEntity.ok(listTask);
    }
    
    @GetMapping(value = "/{id}",produces = {"application/json","application/xml"})
    public ResponseEntity<Optional<Task>> getTask(@PathVariable("id") long id) {
        Optional<Task> task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping(value = "/delete/{id}",produces = {"application/json","application/xml"})
     void deletTaskById(@PathVariable( "id") long id){
        taskService.deleteTask(id);
    }

    @PutMapping(value = "/update/{id}",produces = {"application/json","application/xml"})
    public ResponseEntity<Task> updatTask(@PathVariable(value = "id") long id, @RequestBody TaskDTO taskDTO ) {
            Task task = taskService.updateTask(id, taskDTO);
            return ResponseEntity.ok(task);
    }


    @PostMapping(value="/insert",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> creatTask(@RequestBody TaskDTO taskDTO) {
        Task task = taskService.creatTask(taskDTO);
        return ResponseEntity.ok(task);
    }
}
