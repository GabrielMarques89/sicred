package design.boilerplate.springboot.service;

import design.boilerplate.springboot.exceptions.RegistrationException;
import design.boilerplate.springboot.model.Topic;
import design.boilerplate.springboot.model.User;
import design.boilerplate.springboot.model.Vote;
import design.boilerplate.springboot.repository.VoteRepository;
import design.boilerplate.springboot.security.dto.VoteRequest;
import design.boilerplate.springboot.utils.ExceptionMessageAccessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class VoteValidationService {

	private static final String USERNAME_ALREADY_EXISTS = "username_already_exists";

	private final VoteRepository voteRepository;

	private final ExceptionMessageAccessor exceptionMessageAccessor;

	public void validateVote(Vote vote) {
		checkIfUserAlreadyVoted(vote.getTopic(), vote.getUser());
	}

	public void checkUser(Long topicId, Long userId){	}
	public void checkTopic(Long topicId, Long userId){	}
	public void checkSession(Long topicId, Long userId){	}

	public void checkIfUserAlreadyVoted(Topic topic, User user){
		if(voteRepository.existsByUserAndTopic(user,topic)){
			var message = String.format("Vote already exists for topic {} and user {}", topic, user);

			//TODO: Melhorar, internacionalizar
			var friendlyMessage = "Esse usuário já votou nessa pauta";
			log.warn(message);
			throw new RegistrationException(friendlyMessage);
		}
	}
}
