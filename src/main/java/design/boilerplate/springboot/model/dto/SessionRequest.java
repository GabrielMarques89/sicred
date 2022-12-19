package design.boilerplate.springboot.model.dto;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class SessionRequest {

  public SessionRequest(Long topic, Long duration) {
    this.topic = topic;
    this.duration = duration;
  }

  @NotNull(message = "{topic_not_empty}")
  private Long topic;
  private Long duration;
}