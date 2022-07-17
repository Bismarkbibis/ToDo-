package com.example.demo.controler;

import java.util.List;
import java.util.Optional;

import com.example.demo.exception.CustomeException;
import org.springframework.beans.factory.annotation.Autowired;
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
        System.out.println("ELEMENT 1 "+listTask.get(1));

        return ResponseEntity.ok(listTask);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Task>> getTask(@PathVariable("id") long id) {
        Optional<Task> task = taskService.getTaskById(id);
        System.out.println(" by Id "+task.get());
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/delete/{id}")
    public void deletTaskById(@PathVariable( "id") long id){
        taskService.deleteTask(id);
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<Task> receiveTaskUpdate(@PathVariable(value = "id") long id, @RequestBody TaskDTO taskDTO ) {
        try {
            System.out.println(""+taskDTO.getName());
            Task task = taskService.updateTask(id, taskDTO);
            System.out.println("AAAAAAA     "+taskDTO.getName());

            return ResponseEntity.ok(task);
        } catch (CustomeException e) {
            e.getMessage();
        }
        return null;
    }




    @PostMapping(value="/insert",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> receiveTaskCreated(@RequestBody TaskDTO taskDTO) {
        //TODO: process POST request
        try {
             System.out.println("oookkkkk");
             Task task = taskService.creatTask(taskDTO); 
             System.out.println("no dispp");
             return ResponseEntity.ok(task);
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        return null; 
    }
}
