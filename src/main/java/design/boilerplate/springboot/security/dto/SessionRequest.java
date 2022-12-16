package design.boilerplate.springboot.security.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class SessionRequest {
	private Long topic;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime beginDateTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endDateTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private int duration;

}