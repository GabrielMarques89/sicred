package design.boilerplate.springboot.service;

import design.boilerplate.springboot.model.Topic;
import design.boilerplate.springboot.model.dto.TopicRequest;
import design.boilerplate.springboot.model.dto.TopicResponse;
import design.boilerplate.springboot.model.mapper.TopicMapper;
import design.boilerplate.springboot.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

  private final TopicRepository topicRepository;
  private final TopicValidationService topicValidationService;

  public TopicResponse createTopicV2(TopicRequest topic){
    var entity = createTopic(topic);
    return TopicMapper.INSTANCE.map(entity);
  }

  @Override
  public Topic createTopic(TopicRequest topicRequest) {
    var topic = TopicMapper.INSTANCE.convertToTopic(topicRequest);
    topicValidationService.validateTopic(topicRequest);
    topicRepository.save(topic);
    log.info("Topic created");
    return topic;
  }

  public Topic getTopic(Long id){
    return topicRepository.findById(id).orElse(null);
  }
}
