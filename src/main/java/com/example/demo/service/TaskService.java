package com.example.demo.service;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TaskDTO;
import com.example.demo.exception.CustomeException;
import com.example.demo.model.Task;
import com.example.demo.model.Topic;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.TopicRepository;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TopicRepository topicRepository;

    private Map<String,String> error= new HashMap<>();

    private Date theDate = null;

    public Optional<Task> getTaskById(long id){
        Optional<Task> task = taskRepository.findById(id);
        return task;
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Task updateTask(TaskDTO taskDTO) throws CustomeException {
        Optional<Task> receiveTask= taskRepository.findByName(taskDTO.getName());
        if (receiveTask.isPresent()) {
            Task task = receiveTask.get();
            task.setName(taskDTO.getName());
            task.setDescription(task.getDescription());
         }
         error.put("error update", "name already existant");
         CustomeException ex = new CustomeException("error", error);
         throw ex;      
    }

    public List<Task> getAllTask() {
        List<Task> listTask= taskRepository.findAll();
        return listTask;
    }

    
    
    public Task creatTask(TaskDTO taskDTO) throws CustomeException {
        Optional<Task> receiveTask= taskRepository.findByName(taskDTO.getName());
        Optional<Topic> receiveTopic = topicRepository.findById(taskDTO.getTopic());
        if (receiveTopic.isPresent()) {
            Topic topic = receiveTopic.get();
            if (receiveTask.isPresent()) {
                error.put("error insertion", "name already existant");
          }
          Task task= new Task();
          task.setName(taskDTO.getName());
          task.setDescription(taskDTO.getDescription());
          task.setTopic(topic);
          //gestion date a faire 

          String dateToStr = String.format("%1$tY-%1$tm-%1$td", taskDTO.getDeadline());
          Boolean verification = isBeforeToday(dateToStr);
          if (verification) {
            task.setDeadline(taskDTO.getDeadline());
          } else {
            error.put("error", "date is note receivable");
          }
          taskRepository.save(task);
          return task;
          
        }  else if (!error.isEmpty()){
            error.put("error topic", "topic no existant");
            CustomeException ex = new CustomeException("error", error);
            throw ex;
        }
        return null;
       
    }

    public boolean valideLaDate(String laDate){
        if(laDate.length()==0){
            return false;
        }
        String dateFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        try {
            this.theDate = sdf.parse(laDate);
        } catch (java.text.ParseException e) {
            return false;
        }
        return true;
    }
    
    public boolean isBeforeToday(String laDate){
        
        if(this.valideLaDate(laDate)){
            Date current = new Date();
            if(this.theDate.before(current)){
                return true;
            }
        }
        return false;
    }

}
