package design.boilerplate.springboot.service;

import design.boilerplate.springboot.model.Session;
import design.boilerplate.springboot.model.dto.SessionCreationResponse;
import design.boilerplate.springboot.model.dto.SessionOpenningRequest;
import design.boilerplate.springboot.model.dto.SessionOpenningResponse;
import design.boilerplate.springboot.model.dto.SessionRequest;
import design.boilerplate.springboot.model.mapper.SessionMapper;
import design.boilerplate.springboot.repository.SessionRepository;
import design.boilerplate.springboot.utils.ExceptionMessageAccessor;
import design.boilerplate.springboot.utils.GeneralMessageAccessor;
import java.time.temporal.ChronoUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

  public static final String SESSION_OPENED = "session_opened";
  private final SessionRepository sessionRepository;
  private final VoteService voteService;
  private final SessionValidationService sessionValidationService;
  private final ExceptionMessageAccessor exceptionMessageAccessor;
  private final GeneralMessageAccessor generalMessageAccessor;
  private final String INVALID_SESSION = "invalid_session";


  public SessionCreationResponse createSessionV2(SessionRequest topic) {
    var result = createSession(topic);

    return SessionMapper.INSTANCE.map(result);
  }

  public Session createSession(SessionRequest sessionRequest) {
    log.info("Creating session");
    var session = SessionMapper.INSTANCE.convert(sessionRequest);
    sessionValidationService.validateSession(session);

    return sessionRepository.save(session);
  }

  public Session getSession(Long sessionId) {
    return sessionRepository.findById(sessionId).orElseThrow(
        () -> new RuntimeException(exceptionMessageAccessor.getMessage(null, INVALID_SESSION)));
  }

  public SessionOpenningResponse beginSession(SessionOpenningRequest request) {
    log.info("Opening session");

    var session = getSession(request.getSession());
    session.setEndDateTime(
        session.getBeginDateTime().plus(request.getDuration(), ChronoUnit.MINUTES));
    sessionRepository.save(session);

    var message = generalMessageAccessor.getMessage(null, SESSION_OPENED,
        session.getBeginDateTime(), session.getEndDateTime(), session.getTopic().getName());

    log.info("Session opened");

    return new SessionOpenningResponse(session.getId(), session.getTopic().getName(),
        message);
  }
}
