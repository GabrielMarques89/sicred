package design.boilerplate.springboot.security.dto;

import design.boilerplate.springboot.model.User;
import design.boilerplate.springboot.model.VoteResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VoteRequest {
  private User user;
  private Long session;
  private VoteResult voteResult;
}
