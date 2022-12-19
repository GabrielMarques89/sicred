package design.boilerplate.springboot.model.dto;

import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class TopicResponse {

	private String name;
	private String id;
}