package design.boilerplate.springboot.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Vote count model - Represents a summary of votes")
public class VoteCountDto {

  @Schema(description = "Number of total votes registered in a session")
  private Long countBySession;
  @Schema(description = "Number of pending votes registered in a session")
  private Long pending;
  @Schema(description = "Number of favorable votes registered in a session")
  private Long yesVotes;
  @Schema(description = "Number of negative votes registered in a session")
  private Long noVotes;
}
