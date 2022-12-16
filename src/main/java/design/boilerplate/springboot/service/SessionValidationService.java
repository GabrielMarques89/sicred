package design.boilerplate.springboot.service;

import design.boilerplate.springboot.exceptions.RegistrationException;
import design.boilerplate.springboot.model.Session;
import design.boilerplate.springboot.repository.SessionRepository;
import design.boilerplate.springboot.repository.TopicRepository;
import design.boilerplate.springboot.security.dto.SessionRequest;
import design.boilerplate.springboot.security.dto.TopicRequest;
import design.boilerplate.springboot.security.service.TopicService;
import design.boilerplate.springboot.utils.ExceptionMessageAccessor;
import design.boilerplate.springboot.utils.GeneralMessageAccessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class SessionValidationService {
	private final SessionRepository sessionRepo;
	private final TopicService topicService;

	private final ExceptionMessageAccessor exceptionMessageAccessor;
	private final GeneralMessageAccessor generalMessageAccessor;
	private final String INVALID_TOPIC = "invalid_topic";

	public void validateTopic(Session session) {
		//Sem restrição em relação a pauta.
		// Em tese, seria possível fazer uma votação em multiplas sessões sobre uma mesma pauta
		//desde que fosse obedecida a regra de um voto por usuário por pauta.

		var topic = topicService.getTopic(session.getTopic().getId());
		if(topic == null){

			final String fail_because_of_topic_id = generalMessageAccessor.getMessage(null, INVALID_TOPIC, session.getTopic().getId());
			log.warn("Topic {} not found", session.getTopic().getId());
			throw new RuntimeException(fail_because_of_topic_id);
		}
	}
}
