package design.boilerplate.springboot.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import design.boilerplate.springboot.exceptions.RegistrationException;
import design.boilerplate.springboot.repository.SessionRepository;
import design.boilerplate.springboot.utils.ExceptionMessageAccessor;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import util.BaseSicredServiceTest;
import util.ModelHelper;

public class SessionValidationServiceTest extends BaseSicredServiceTest {
  @Mock
  private SessionRepository sessionRepository;
  @InjectMocks
  private SessionValidationService sessionValidationService;

  @Mock
  private TopicService topicService;

  @Mock
  private ExceptionMessageAccessor exceptionMessageAccessor;

  @Test(expected = RegistrationException.class)
  public void testCreateSessionValidationFail() {
    var session = ModelHelper.mockSession();
    when(sessionRepository.existsByTopic(any())).thenReturn(true);

    sessionValidationService.validateSession(session);
  }

  @Test(expected = RegistrationException.class)
  public void testCreateSessionValidationNoTopic() {
    var session = ModelHelper.mockSession();
    when(sessionRepository.existsByTopic(any())).thenReturn(false);
    when(topicService.getTopic(any())).thenReturn(null);

    sessionValidationService.validateSession(session);
  }
}