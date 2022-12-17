package design.boilerplate.springboot.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessionReportDto {

	private String topic;
	private PendingVotingUsers votes;

}
