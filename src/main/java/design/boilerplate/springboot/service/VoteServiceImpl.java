package design.boilerplate.springboot.service;

import design.boilerplate.springboot.model.Session;
import design.boilerplate.springboot.model.User;
import design.boilerplate.springboot.model.VoteResult;
import design.boilerplate.springboot.model.dto.VoteCountDto;
import design.boilerplate.springboot.model.dto.VoteRequest;
import design.boilerplate.springboot.model.mapper.VoteMapper;
import design.boilerplate.springboot.repository.SessionRepository;
import design.boilerplate.springboot.repository.VoteRepository;
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
  public void registerVote(VoteRequest req, User user) {
    var vote = VoteMapper.INSTANCE.convertToVote(req);
    vote.setUser(user);
    vote = voteValidationService.validateVote(vote);

    voteRepository.save(vote);
    log.info("Vote registered");
  }

  @Override
  public VoteCountDto countVotes(Long sessionId) {
    var session = sessionRepository.findById(sessionId).orElseThrow();
    return countVotes(session);
  }

  @Override
  public VoteCountDto countVotes(Session session) {
    log.info("Counting votes");

    var votes = new VoteCountDto();

    var votesByTopic = voteRepository.countByTopic(session.getTopic());
    votes.setTotalYesVoteOnTopic(
        voteRepository.countByTopicAndVoteResult(session.getTopic(), VoteResult.YES));
    votes.setTotalNoVoteOnTopic(votesByTopic - votes.getTotalYesVoteOnTopic());
    votes.setTotalTopicVotes(votesByTopic);
    votes.setTotalTopicPendingVotes(userService.countUsers() - votesByTopic);

    votes.setCountBySession(voteRepository.countBySession(session));
    return votes;
  }
}
