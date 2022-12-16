package design.boilerplate.springboot.security.dto;

import design.boilerplate.springboot.model.Session;
import design.boilerplate.springboot.model.Topic;
import design.boilerplate.springboot.model.User;
import design.boilerplate.springboot.model.VoteResult;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VoteRequest {
  private Long user;
  private Long session;
  private Long topic;
  private VoteResult voteResult;
}
