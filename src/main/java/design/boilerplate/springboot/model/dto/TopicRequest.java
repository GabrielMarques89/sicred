package design.boilerplate.springboot.model.dto;

import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class TopicRequest {

  public TopicRequest(String name) {
    this.name = name;
  }

  @NotEmpty(message = "{login_username_not_empty}")
  private String name;
}