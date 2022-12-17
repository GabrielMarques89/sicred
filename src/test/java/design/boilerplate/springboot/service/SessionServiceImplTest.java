package design.boilerplate.springboot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static util.DtoHelper.mockRequest;
import static util.DtoHelper.mockSessionOpenningRequest;

import design.boilerplate.springboot.exceptions.RegistrationException;
import design.boilerplate.springboot.model.Session;
import design.boilerplate.springboot.repository.SessionRepository;
import design.boilerplate.springboot.utils.ExceptionMessageAccessor;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import util.BaseSicredServiceTest;
import util.ModelHelper;

public class SessionServiceImplTest extends BaseSicredServiceTest {

  @InjectMocks
  private SessionServiceImpl service;

  @Mock
  private SessionRepository sessionRepository;
  @Mock
  private SessionValidationService sessionValidationService;

  @Mock
  private ExceptionMessageAccessor exceptionMessageAccessor;

  @Mock
  private VoteService voteService;

  @Test
  public void testCreateSessionSuccess() {
    var req = mockRequest();
    service.createSession(req);
  }

  @Test(expected = RegistrationException.class)
  public void testCreateSessionValidationFail() {
    doThrow(RegistrationException.class).when(sessionValidationService).validateSession(any());
    var req = mockRequest();
    service.createSession(req);

    verify(sessionRepository, times(0)).save(any());
    verify(sessionRepository, times(1)).existsByTopic(any());
  }

  @Test()
  public void testGetSessionSuccess() {
    Session session = mockValidSession();

    var result = service.getSession(1L);

    assertEquals(session, result);
    verify(sessionRepository, times(1)).findById(any());
  }

  @Test(expected = RuntimeException.class)
  public void testGetSessionFail() {
    mockFindSessionOnRepo(java.util.Optional.empty());

    service.getSession(1L);
    verify(sessionRepository, times(1)).findById(any());
  }

  @Test
  public void beginSessionSuccess() {
    var session = ModelHelper.mockSession();
    mockFindSessionOnRepo(Optional.of(session));

    var request = mockSessionOpenningRequest();
    service.beginSession(request);

    assertEquals(session.getEndDateTime(),
        session.getBeginDateTime().plus(request.getDuration(), ChronoUnit.MINUTES));
    verify(sessionRepository, times(1)).save(session);
  }

  private void mockFindSessionOnRepo(Optional<Session> model) {
    when(sessionRepository.findById(any())).thenReturn(model);
  }

  private Session mockValidSession() {
    var session = ModelHelper.mockSession();
    mockFindSessionOnRepo(Optional.of(session));

    return session;
  }
}