package design.boilerplate.springboot.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VoteCountDto {

  private Long countBySession;
  private Long pending;
  private Long yesVotes;
  private Long noVotes;
}
