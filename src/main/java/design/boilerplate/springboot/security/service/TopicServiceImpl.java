package design.boilerplate.springboot.security.service;

import design.boilerplate.springboot.model.Topic;
import design.boilerplate.springboot.repository.TopicRepository;
import design.boilerplate.springboot.security.dto.TopicRequest;
import design.boilerplate.springboot.security.mapper.TopicMapper;
import design.boilerplate.springboot.service.TopicValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

  private final TopicRepository topicRepository;
  private final TopicValidationService topicValidationService;

  @Override
  public void createTopic(TopicRequest topicRequest) {
    var topic = TopicMapper.INSTANCE.convertToTopic(topicRequest);
    topicValidationService.validateTopic(topicRequest);
    topicRepository.save(topic);
    log.info("Topic created");
  }

  public Topic getTopic(Long id){
    return topicRepository.findById(id).orElse(null);
  }
}
