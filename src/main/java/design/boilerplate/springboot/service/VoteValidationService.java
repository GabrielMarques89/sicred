package design.boilerplate.springboot.service;

import design.boilerplate.springboot.exceptions.RegistrationException;
import design.boilerplate.springboot.model.Session;
import design.boilerplate.springboot.model.Topic;
import design.boilerplate.springboot.model.User;
import design.boilerplate.springboot.model.Vote;
import design.boilerplate.springboot.repository.SessionRepository;
import design.boilerplate.springboot.repository.VoteRepository;
import design.boilerplate.springboot.utils.ExceptionMessageAccessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class VoteValidationService {

	private static final String SESSION_NOT_FOUND = "session_not_found";
	private static final String USER_ALREADY_VOTED = "user_already_voted";

	private final VoteRepository voteRepository;
	private final SessionRepository sessionRepository;

	private final ExceptionMessageAccessor exceptionMessageAccessor;

	public Vote validateVote(Vote vote) {
		var session = checkSession(vote.getSession().getId());
		vote.setTopic(session.getTopic());
		checkIfUserAlreadyVoted(session.getTopic(), vote.getUser());
		//TODO: Regra para validar se o usuário ainda pode votar (o voto precisa ser entre o início e fim da votação e só pode ocorrer caso a votação esteja aberta (possua data fim).

		return vote;
	}

	public Session checkSession(Long id){
		var session = sessionRepository.findById(id);
		if(session.isEmpty()){
			throw new RegistrationException(exceptionMessageAccessor.getMessage(null, SESSION_NOT_FOUND, String.valueOf(id)));
		}

		return session.get();
	}

	public void checkIfUserAlreadyVoted(Topic topic, User user){
		if(voteRepository.existsByUserAndTopic(user,topic)){
			var message = String.format("Vote already exists for topic {} and user {}", topic.getId(), user.getId());
			log.warn(message);

			throw new RegistrationException(exceptionMessageAccessor.getMessage(null, USER_ALREADY_VOTED));
		}
	}
}
