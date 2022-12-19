package design.boilerplate.springboot.model.dto;

import design.boilerplate.springboot.model.User;
import design.boilerplate.springboot.model.VoteResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VoteRequest {

  public VoteRequest(Long session, VoteResult voteResult) {
    this.session = session;
    this.voteResult = voteResult;
  }

  private Long session;
  private VoteResult voteResult;
}