package com.example.demo.controler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.dto.TopicDTO;
import com.example.demo.exception.CustomeException;
import com.example.demo.model.Topic;
import com.example.demo.service.TopicService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/topic")
@CrossOrigin("*")
public class TopicController {

    @Autowired
    private TopicService topicService;

   @GetMapping("/{name}")
   public ResponseEntity<Optional<Topic>> getByName(@RequestParam(value = "name") String name) {
      Optional<Topic> topic= topicService.getTopicByName(name);
      return ResponseEntity.ok(topic);
   }

   @GetMapping("/all")
   public ResponseEntity<List<Topic>> getAll() {
    List<Topic> topic = topicService.getAllTopic();
      return ResponseEntity.ok(topic);
   }

   @PostMapping(value="insert",produces = { "application/json", "application/xml" })
   public ResponseEntity<Topic> postMethodName(@RequestBody TopicDTO topicDTO) {
       
      Topic topic;
      try {
         topic = topicService.creatTopic(topicDTO.getName());
         return ResponseEntity.ok(topic);
      } catch (CustomeException e) {
         // TODO Auto-generated catch block
         e.getMessage();
      }
      return null;
       
   }
   

   
}
