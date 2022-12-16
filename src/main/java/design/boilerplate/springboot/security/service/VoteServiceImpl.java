package design.boilerplate.springboot.security.service;

import design.boilerplate.springboot.repository.VoteRepository;
import design.boilerplate.springboot.security.dto.VoteRequest;
import design.boilerplate.springboot.security.mapper.VoteMapper;
import design.boilerplate.springboot.service.SessionValidationService;
import design.boilerplate.springboot.service.VoteValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {
	private final VoteRepository voteRepository;
	private final VoteValidationService voteValidationService;

	public void registerVote(VoteRequest req){
		log.info("Registering vote");

		var vote = VoteMapper.INSTANCE.convertToVote(req);
		voteValidationService.validateVote(vote);

		voteRepository.save(vote);
		log.info("Vote registered");
	}
}
