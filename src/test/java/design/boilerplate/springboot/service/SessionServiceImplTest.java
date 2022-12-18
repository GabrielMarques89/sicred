package design.boilerplate.springboot.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static util.DtoHelper.mockSessionOpenningRequest;
import static util.DtoHelper.mockSessionRequest;

import design.boilerplate.springboot.exceptions.RegistrationException;
import design.boilerplate.springboot.model.Session;
import design.boilerplate.springboot.repository.SessionRepository;
import design.boilerplate.springboot.utils.GeneralMessageAccessor;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.BaseSicredTest;
import util.DtoHelper;
import util.ModelHelper;

public class SessionServiceImplTest extends BaseSicredTest {

  @InjectMocks
  private SessionServiceImpl service;
  @Mock
  private SessionRepository sessionRepository;
  @Mock
  private SessionValidationService sessionValidationService;
  @Mock
  private GeneralMessageAccessor generalMessageAccessor;

  @Test
  public void testCreateSessionSuccess() {
    var req = mockSessionRequest();
    service.createSession(req);
  }

  @Test
  public void remover(){

    var r = DtoHelper.generateUserRegistrationRequestAsString();
    System.out.println(r);
  }



  @BeforeMethod()
  public void resetCount(){
    clearInvocations(sessionRepository);
  }

  @Test(expectedExceptions = RegistrationException.class)
  public void testCreateSessionValidationFail() {
    doThrow(RegistrationException.class).when(sessionValidationService).validateSession(any());
    var req = mockSessionRequest();
    service.createSession(req);

    verify(sessionRepository, times(0)).save(any());
  }

  @Test()
  public void testGetSessionSuccess() {
    Session session = mockValidSession();

    var result = service.getSession(1L);

    assertEquals(session, result);
    verify(sessionRepository, times(1)).findById(any());
  }

  @Test(expectedExceptions = RuntimeException.class)
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