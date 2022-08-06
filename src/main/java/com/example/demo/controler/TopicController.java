package com.example.demo.controler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.TopicDTO;
import com.example.demo.exception.CustomeException;
import com.example.demo.model.Topic;
import com.example.demo.service.TopicService;

@Controller
@RequestMapping("/topic")
@CrossOrigin("*")
public class TopicController {

    @Autowired
    private TopicService topicService;

   @GetMapping("/{name}")
    ResponseEntity<Optional<Topic>> receiveByName(@PathVariable(value = "name") String name) {
      System.out.println("name  : "+name);
      Optional<Topic> topic= topicService.getTopicByName(name);
      return ResponseEntity.ok(topic);
   }

   @DeleteMapping(value = "/delete/{id}",produces = {"application/xml","application/json"})
   void receiveTopicDelete(@PathVariable(value = "id") long id){
           topicService.deleteTopic(id);
   }

   @GetMapping(value = "id/{id}",produces = {"application/json", "application/xml"})
   ResponseEntity<Optional<Topic>> receiveTopicById(@PathVariable(value = "id") long id){
      Optional<Topic> topic = topicService.getTopicById(id);
      return ResponseEntity.ok(topic);
   }

   @PutMapping("/update/{id}")
   ResponseEntity<Topic> receiveTopicUpdate(@PathVariable(value = "id") long id,@RequestBody TopicDTO topicDTO ){
         Topic topic = topicService.updateTopic(id, topicDTO);
         return ResponseEntity.ok(topic);

   }

   @GetMapping("/all")
   public ResponseEntity<List<Topic>> receiveAllTopic() {
    List<Topic> topic = topicService.getAllTopic();
      return ResponseEntity.ok(topic);
   }

   @PostMapping(value="insert",produces = { "application/json", "application/xml" })
   public ResponseEntity<Topic> creatTask(@RequestBody TopicDTO topicDTO) {

      Topic topic= topicService.creatTopic(topicDTO.getName());
         return ResponseEntity.ok(topic);

   }
}
