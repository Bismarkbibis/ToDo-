package com.example.demo.service;

import java.util.*;

import com.example.demo.dto.TopicDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.CustomeException;
import com.example.demo.model.Topic;
import com.example.demo.repository.TopicRepository;


/**
 * TaskService
 * Bis
 */ 
@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    private Calendar dateCreation = Calendar.getInstance();
    private CustomeException ex = null;


    private Map<String,String> error= new HashMap<>();

    public List<Topic> getAllTopic () {
        return topicRepository.findAll();
    }

    public Optional<Topic> getTopicById(long id){
       return topicRepository.findById(id);
    }


    public Optional<Topic> getTopicByName(String name) {
        return topicRepository.findByName(name);
    }
    
    public void deleteTopic(long id) throws CustomeException {
        Optional<Topic> topic =topicRepository.findById(id);
        if (topic.isPresent()) {
            topicRepository.deleteById(id);
        }
        throw new CustomeException("Topic no present");
    }


    public Topic creatTopic(String name) throws CustomeException {
        Optional<Topic> getTopicBDD= topicRepository.findByName(name);

        if (getTopicBDD.isPresent()) {
            throw new CustomeException("Topi"," name :  "+name);
        } 
        Topic topic = new Topic();
        topic.setName(name);
        topic.setCreation(dateCreation.getTime());
        topicRepository.save(topic);
        return topic;
    }

    public Topic updateTopic(long id, TopicDTO topicDTO) throws CustomeException {
        Optional<Topic> topic = topicRepository.findById(id);
        if (topic.isPresent()) {
            Topic topic1= topic.get();
            topic1.setName(topicDTO.getName());
            Calendar calendar = Calendar.getInstance();
            topic1.setCreation(calendar.getTime());
            topicRepository.save(topic1);
            return topic1;
        }
        throw new CustomeException("Topic ","id : ",id);
    }
}