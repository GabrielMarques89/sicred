package design.boilerplate.springboot.security.service;

import design.boilerplate.springboot.model.Session;
import design.boilerplate.springboot.model.VoteResult;
import design.boilerplate.springboot.repository.SessionRepository;
import design.boilerplate.springboot.repository.VoteRepository;
import design.boilerplate.springboot.security.dto.VoteCountDto;
import design.boilerplate.springboot.security.dto.VoteRequest;
import design.boilerplate.springboot.security.mapper.VoteMapper;
import design.boilerplate.springboot.service.VoteValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

  private final VoteRepository voteRepository;
  private final UserService userService;
  private final SessionRepository sessionRepository;
  private final VoteValidationService voteValidationService;

  @Override
  public void registerVote(VoteRequest req) {
    var vote = VoteMapper.INSTANCE.convertToVote(req);
    vote = voteValidationService.validateVote(vote);

    voteRepository.save(vote);
    log.info("Vote registered");
  }

  @Override
  public VoteCountDto countVotes(Long sessionId){
    var session = sessionRepository.findById(sessionId).orElseThrow();
    return countVotes(session);
  }

  @Override
  public VoteCountDto countVotes(Session session){
    log.info("Counting votes");

    var votes = new VoteCountDto();

    var votesByTopic = voteRepository.countByTopic(session.getTopic());
    votes.setTotalYesVoteOnTopic(voteRepository.countByTopicAndVoteResult(session.getTopic(), VoteResult.YES));
    votes.setTotalNoVoteOnTopic(votesByTopic - votes.getTotalYesVoteOnTopic());
    votes.setTotalTopicVotes(votesByTopic);
    votes.setTotalTopicPendingVotes(userService.countUsers() - votesByTopic);

    votes.setCountBySession(voteRepository.countBySession(session));
    return votes;
  }
}
