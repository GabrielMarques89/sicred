package design.boilerplate.springboot.service.implementations;

import design.boilerplate.springboot.model.dto.SessionCreationResponse;
import design.boilerplate.springboot.model.dto.SessionDto;
import design.boilerplate.springboot.model.dto.SessionRequest;
import design.boilerplate.springboot.model.entities.Session;
import design.boilerplate.springboot.model.mapper.SessionMapper;
import design.boilerplate.springboot.repository.SessionRepository;
import design.boilerplate.springboot.service.interfaces.SessionService;
import design.boilerplate.springboot.service.interfaces.VoteService;
import design.boilerplate.springboot.service.validations.SessionValidationService;
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
  private final String INVALID_DURATION = "invalid_duration";


  public void createSession(SessionRequest request) {
    createSessionV2(request);
  }

  public SessionCreationResponse createSessionV2(SessionRequest sessionRequest) {
    log.info("Creating session");
    var session = SessionMapper.INSTANCE.convert(sessionRequest);
    session.setBeginDateTime(LocalDateTime.now());
    var duration = sessionRequest.getDuration();
    if (duration != null &&  duration > 0) {
      session.setEndDateTime(session.getBeginDateTime().plusMinutes(sessionRequest.getDuration()));
    } else if(duration < 0){
      throw new RuntimeException(exceptionMessageAccessor.getMessage((INVALID_DURATION)));
    } else {
      session.setEndDateTime(session.getBeginDateTime().plusMinutes(1));
    }

    sessionValidationService.validateSession(session);
    sessionRepository.save(session);

    return SessionMapper.INSTANCE.map(session);
  }

//  public SessionDto getSession(Long sessionId) {
//    var entity = sessionRepository.findById(sessionId).orElseThrow(
//        () -> new RuntimeException(exceptionMessageAccessor.getMessage((INVALID_SESSION))));
//
//    return SessionMapper.INSTANCE.mapToDto(entity);
//  }
}
