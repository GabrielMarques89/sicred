package design.boilerplate.springboot.security.service;


import design.boilerplate.springboot.model.Session;
import design.boilerplate.springboot.security.dto.VoteCountDto;
import design.boilerplate.springboot.security.dto.VoteRequest;

public interface VoteService {
  void registerVote(VoteRequest req);

  VoteCountDto countVotes(Session session);
  VoteCountDto countVotes(Long session);
}
