package design.boilerplate.springboot.model.dto;

import design.boilerplate.springboot.model.VoteResult;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class VoteRequest {

  public VoteRequest(Long session, VoteResult voteResult) {
    this.session = session;
    this.voteResult = voteResult;
  }

  @NotNull(message = "{vote_session_not_empty}")
  private Long session;
  private VoteResult voteResult;
}
