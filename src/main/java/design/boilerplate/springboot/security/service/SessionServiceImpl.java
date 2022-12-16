package design.boilerplate.springboot.security.service;

import design.boilerplate.springboot.repository.SessionRepository;
import design.boilerplate.springboot.security.dto.SessionRequest;
import design.boilerplate.springboot.security.dto.TopicRequest;
import design.boilerplate.springboot.security.mapper.SessionMapper;
import design.boilerplate.springboot.security.mapper.TopicMapper;
import design.boilerplate.springboot.service.SessionValidationService;
import design.boilerplate.springboot.service.TopicValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

	private final SessionRepository sessionRepository;
	private final TopicService topicService;
	private final SessionValidationService sessionValidationService;

	public void createSession(SessionRequest sessionRequest){
		log.info("Creating session");
		var session = SessionMapper.INSTANCE.map(sessionRequest);
		sessionValidationService.validateTopic(session);

		sessionRepository.save(session);
	}
}
