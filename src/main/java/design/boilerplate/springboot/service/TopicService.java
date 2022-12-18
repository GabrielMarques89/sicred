package design.boilerplate.springboot.service;

import design.boilerplate.springboot.model.Topic;
import design.boilerplate.springboot.model.dto.TopicRequest;
import design.boilerplate.springboot.model.dto.TopicResponse;


public interface TopicService {
    public Topic createTopic(TopicRequest topic);
    public TopicResponse createTopicV2(TopicRequest topic);

    public Topic getTopic(Long id);
}
