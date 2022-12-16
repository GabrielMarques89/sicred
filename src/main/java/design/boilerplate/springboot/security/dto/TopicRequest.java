package design.boilerplate.springboot.security.dto;

import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class TopicRequest {

	@NotEmpty(message = "{login_username_not_empty}")
	private String name;
}