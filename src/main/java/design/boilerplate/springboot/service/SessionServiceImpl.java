package design.boilerplate.springboot.service;

import design.boilerplate.springboot.model.Session;
import design.boilerplate.springboot.model.dto.SessionCreationResponse;
import design.boilerplate.springboot.model.dto.SessionRequest;
import design.boilerplate.springboot.model.mapper.SessionMapper;
import design.boilerplate.springboot.repository.SessionRepository;
import design.boilerplate.springboot.utils.ExceptionMessageAccessor;
import design.boilerplate.springboot.utils.GeneralMessageAccessor;
import java.time.LocalDateTime;
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
    session.setBeginDateTime(LocalDateTime.now());
    if (sessionRequest.getDuration() != 0) {
      session.setEndDateTime(session.getBeginDateTime().plusMinutes(sessionRequest.getDuration()));
    } else {
      session.setEndDateTime(session.getBeginDateTime().plusMinutes(1));
    }

    sessionValidationService.validateSession(session);

    return sessionRepository.save(session);
  }

  public Session getSession(Long sessionId) {
    return sessionRepository.findById(sessionId).orElseThrow(
        () -> new RuntimeException(exceptionMessageAccessor.getMessage(null, INVALID_SESSION)));
  }
}
