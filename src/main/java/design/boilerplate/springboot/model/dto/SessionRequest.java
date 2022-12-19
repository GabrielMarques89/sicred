package design.boilerplate.springboot.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class SessionRequest {

  public SessionRequest(Long topic, Long duration) {
    this.topic = topic;
    this.duration = duration;
  }

  private Long topic;
  private Long duration;
}