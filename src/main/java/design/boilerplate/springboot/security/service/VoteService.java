package design.boilerplate.springboot.security.service;


import design.boilerplate.springboot.security.dto.VoteRequest;

public interface VoteService {
  void registerVote(VoteRequest req);

}
