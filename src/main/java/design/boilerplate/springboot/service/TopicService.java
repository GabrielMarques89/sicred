package design.boilerplate.springboot.service;

import design.boilerplate.springboot.model.Topic;
import design.boilerplate.springboot.model.dto.TopicRequest;
import design.boilerplate.springboot.model.dto.TopicResponse;


public interface TopicService {

  Topic createTopic(TopicRequest topic);

  TopicResponse createTopicV2(TopicRequest topic);

  Topic getTopic(Long id);
}
