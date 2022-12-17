package design.boilerplate.springboot.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VoteCountDto {
  private Long countBySession;
  private Long TotalTopicVotes;
  private Long TotalTopicPendingVotes;
  private Long TotalYesVoteOnTopic;
  private Long TotalNoVoteOnTopic;
}
