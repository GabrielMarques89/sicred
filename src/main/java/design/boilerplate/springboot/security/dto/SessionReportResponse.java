package design.boilerplate.springboot.security.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessionReportResponse {

	private List<SessionReportDto> reports;
	private int count;
}
