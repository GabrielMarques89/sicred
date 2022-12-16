package design.boilerplate.springboot.security.service;

import design.boilerplate.springboot.model.Topic;
import design.boilerplate.springboot.security.dto.TopicRequest;


public interface TopicService {
    public void createTopic(TopicRequest topic);

    public Topic getTopic(Long id);
}
