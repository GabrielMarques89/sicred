package design.boilerplate.springboot.security.service;

import design.boilerplate.springboot.model.Session;
import design.boilerplate.springboot.repository.SessionRepository;
import design.boilerplate.springboot.security.dto.SessionOpenningRequest;
import design.boilerplate.springboot.security.dto.SessionReportDto;
import design.boilerplate.springboot.security.dto.SessionRequest;
import design.boilerplate.springboot.security.dto.VoteCountDto;
import design.boilerplate.springboot.security.mapper.SessionMapper;
import design.boilerplate.springboot.service.SessionValidationService;
import design.boilerplate.springboot.utils.ExceptionMessageAccessor;
import java.time.temporal.ChronoUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

	private final SessionRepository sessionRepository;
	private final VoteService voteService;
	private final SessionValidationService sessionValidationService;
	private final ExceptionMessageAccessor exceptionMessageAccessor;
	private final String INVALID_SESSION = "invalid_session";

	public void createSession(SessionRequest sessionRequest){
		log.info("Creating session");
		var session = SessionMapper.INSTANCE.convert(sessionRequest);
		sessionValidationService.validateSession(session);

		sessionRepository.save(session);
	}

	public VoteCountDto countVotes(Long sessionId){
		log.info("Counting votes");
		var session = sessionRepository.findById(sessionId).orElseThrow();
		return voteService.countVotes(session);
	}

	public Session getSession(Long sessionId){
		return sessionRepository.findById(sessionId).orElseThrow(() -> new RuntimeException(exceptionMessageAccessor.getMessage(null, INVALID_SESSION)));
	}

	public void beginSession(SessionOpenningRequest request){
		log.info("Opening session");

		var session = getSession(request.getSession());
		session.setEndDateTime(session.getBeginDateTime().plus(request.getDuration(), ChronoUnit.MINUTES));
		sessionRepository.save(session);
	}
}
