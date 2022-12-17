package design.boilerplate.springboot.service;

import static org.mockito.ArgumentMatchers.any;

import design.boilerplate.springboot.repository.TopicRepository;
import design.boilerplate.springboot.utils.ExceptionMessageAccessor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.Test;
import util.BaseSicredTest;

public class TopicValidationServiceTest extends BaseSicredTest {
  @Mock
  private TopicRepository sessionRepository;
  @InjectMocks
  private TopicValidationService sessionValidationService;

  @Mock
  private TopicService topicService;

  @Mock
  private ExceptionMessageAccessor exceptionMessageAccessor;

  @Test void something(){

  }
}