package design.boilerplate.springboot.service;

import design.boilerplate.springboot.model.Topic;
import design.boilerplate.springboot.model.dto.TopicRequest;


public interface TopicService {
    public void createTopic(TopicRequest topic);

    public Topic getTopic(Long id);
}
