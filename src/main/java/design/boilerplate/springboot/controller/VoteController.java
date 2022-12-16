package design.boilerplate.springboot.controller;

import design.boilerplate.springboot.security.dto.RegistrationRequest;
import design.boilerplate.springboot.security.dto.RegistrationResponse;
import design.boilerplate.springboot.security.dto.SessionRequest;
import design.boilerplate.springboot.security.dto.TopicRequest;
import design.boilerplate.springboot.security.dto.VoteRequest;
import design.boilerplate.springboot.security.service.SessionService;
import design.boilerplate.springboot.security.service.TopicService;
import design.boilerplate.springboot.security.service.UserService;
import design.boilerplate.springboot.security.service.VoteService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/vote")
public class VoteController {
	private final VoteService voteService;

	@PostMapping()
	public ResponseEntity<RegistrationResponse> registerVote(@Valid @RequestBody VoteRequest req) {
		voteService.registerVote(req);

		return ResponseEntity.status(HttpStatus.CREATED).body(new RegistrationResponse("Topic created"));
	}
}
