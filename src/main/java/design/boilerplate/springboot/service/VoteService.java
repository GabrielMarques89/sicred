package design.boilerplate.springboot.service;


import design.boilerplate.springboot.model.Session;
import design.boilerplate.springboot.model.User;
import design.boilerplate.springboot.model.dto.VoteCountDto;
import design.boilerplate.springboot.model.dto.VoteRequest;

public interface VoteService {
  void registerVote(VoteRequest req, User user);

  VoteCountDto countVotes(Session session);
  VoteCountDto countVotes(Long session);
}
