package design.boilerplate.springboot.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class SessionRequest {

	public SessionRequest(Long topic, LocalDateTime beginDateTime) {
		this.topic = topic;
		this.beginDateTime = beginDateTime;
	}

	public SessionRequest(LocalDateTime beginDateTime) {
		this.beginDateTime = beginDateTime;
	}

	private Long topic;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime beginDateTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endDateTime;
}