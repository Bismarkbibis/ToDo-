package com.example.demo.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.CustomeException;
import com.example.demo.model.Topic;
import com.example.demo.repository.TopicRepository;


/**
 * TaskService
 */ 
@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;


    private Map<String,String> error= new HashMap<>();

    public List<Topic> getAllTopic () {
        List<Topic> listTopic=topicRepository.findAll();
        return listTopic;
    }


    public Optional<Topic> getTopicByName(String name) {
        return topicRepository.findByName(name);
    }
    
    public void deleteTopic(long id) throws CustomeException {
        Optional<Topic> topic =topicRepository.findById(id);
        if (topic.isPresent()) {
            topicRepository.deleteById(id);
        }
            error.put("error insert", "Topic exitant");
            CustomeException ex = new CustomeException();
            throw ex;
    }
    
    public Topic creatTopic(String name) throws CustomeException {
        Optional<Topic> getTopicBDD= topicRepository.findByName(name);

        if (getTopicBDD.isPresent()) {
            error.put("error insert", "Topic exitant");
            CustomeException ex = new CustomeException();
            throw ex;
        } 
        Topic topic = new Topic();
        topic.setName(name);
        Calendar dateCreation = Calendar.getInstance();
        
        topic.setCreation(dateCreation.getTime());
        topicRepository.save(topic);
        return topic;
    }

     
}