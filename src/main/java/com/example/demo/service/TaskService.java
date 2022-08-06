package com.example.demo.service;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
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
    private  TaskRepository taskRepository;

    @Autowired
    private  TopicRepository topicRepository;

    private Date theDate ;

    public Optional<Task> getTaskById(long id){
        Optional<Task> task = taskRepository.findById(id);
        return task;
    }

    public void deleteTask(long id) {
        boolean exist = taskRepository.existsById(id);
        if (!exist) {
            throw  new CustomeException("task id : "+id+ " not existant");
        }
        taskRepository.deleteById(id);
    }


    public Task updateTask(long id,TaskDTO taskDTO) {
            Task task= taskRepository.findById(id).orElseThrow(()-> new CustomeException("Task","id = ",id));
            task.setName(taskDTO.getName());
            task.setDescription(task.getDescription());
            taskRepository.save(task);
            return task;
    }

    public List<Task> getAllTask() {
        List<Task> listTask= taskRepository.findAll();
        return listTask;
    }

    public Task creatTask(TaskDTO taskDTO) throws CustomeException {
        Optional<Topic> receiveTopic = topicRepository.findById(taskDTO.getTopic());
        if (receiveTopic.isPresent()) {
          Topic topic = receiveTopic.get();

          Task task= new Task();
          task.setName(taskDTO.getName());
          task.setDescription(taskDTO.getDescription());
          task.setTopic(topic);
          //gestion date
          String dateToStr = String.format("%1$tY-%1$tm-%1$td", taskDTO.getDeadline());
          Boolean verification = isBeforeToday(dateToStr);
          //TODO verifier la date de mise en ligne
          if (verification==false) {
            task.setDeadline(taskDTO.getDeadline());
          } else {
              throw new CustomeException("ate is note receivable");
          }
          taskRepository.save(task);
          return task;
        }
           throw new CustomeException("Task is present");
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
