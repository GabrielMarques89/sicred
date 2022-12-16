package design.boilerplate.springboot.controller;

import design.boilerplate.springboot.security.dto.RegistrationRequest;
import design.boilerplate.springboot.security.dto.SessionRequest;
import design.boilerplate.springboot.security.dto.TopicRequest;
import design.boilerplate.springboot.security.dto.RegistrationResponse;
import design.boilerplate.springboot.security.dto.VoteRequest;
import design.boilerplate.springboot.security.service.SessionService;
import design.boilerplate.springboot.security.service.TopicService;
import design.boilerplate.springboot.security.service.UserService;
import design.boilerplate.springboot.security.service.VoteService;
import design.boilerplate.springboot.security.service.VoteServiceImpl;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {

	private final UserService userService;
	private final TopicService topicService;
	private final VoteService voteService;
	private final SessionService sessionService;

	@PostMapping("/user")
	public ResponseEntity<RegistrationResponse> registrationRequest(@Valid @RequestBody RegistrationRequest registrationRequest) {

		final RegistrationResponse registrationResponse = userService.registration(registrationRequest);

		return ResponseEntity.status(HttpStatus.CREATED).body(registrationResponse);
	}

	@PostMapping("/topic")
	public ResponseEntity<RegistrationResponse> createTopic(@Valid @RequestBody TopicRequest topicRequest) {

		topicService.createTopic(topicRequest);

		return ResponseEntity.status(HttpStatus.CREATED).body(new RegistrationResponse("Topic created"));
	}

	@PostMapping("/session")
	public ResponseEntity<RegistrationResponse> createSession(@Valid @RequestBody SessionRequest sessionRequest) {
		sessionService.createSession(sessionRequest);

		return ResponseEntity.status(HttpStatus.CREATED).body(new RegistrationResponse("Topic created"));
	}

	@PostMapping("/vote")
	public ResponseEntity<RegistrationResponse> registerVote(@Valid @RequestBody VoteRequest req) {
		voteService.registerVote(req);

		return ResponseEntity.status(HttpStatus.CREATED).body(new RegistrationResponse("Topic created"));
	}
}
