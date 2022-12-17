package design.boilerplate.springboot.controller;

import design.boilerplate.springboot.model.dto.RegistrationResponse;
import design.boilerplate.springboot.model.dto.TopicRequest;
import design.boilerplate.springboot.service.TopicService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/topic")
public class TopicController {

  private final TopicService topicService;

  @PostMapping()
  public ResponseEntity<RegistrationResponse> createTopic(
      @Valid @RequestBody TopicRequest topicRequest) {

    topicService.createTopic(topicRequest);

    return ResponseEntity.status(HttpStatus.CREATED).body(null);
  }
}
