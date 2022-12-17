package design.boilerplate.springboot.controller;

import design.boilerplate.springboot.model.dto.RegistrationResponse;
import design.boilerplate.springboot.model.dto.VoteCountDto;
import design.boilerplate.springboot.model.dto.VoteRequest;
import design.boilerplate.springboot.service.UserService;
import design.boilerplate.springboot.service.VoteService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	private final UserService userService;

	@PostMapping()
	public ResponseEntity<RegistrationResponse> registerVote(@Valid @RequestBody VoteRequest req) {
		req.setUser(getLoggedUser());
		voteService.registerVote(req);

		return ResponseEntity.status(HttpStatus.CREATED).body(new RegistrationResponse("Your vote has been registered"));
	}

	@GetMapping("/countBySession")
	public ResponseEntity<VoteCountDto> countBySession(Long sessionId) {
		var result = voteService.countVotes(sessionId);

		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	private design.boilerplate.springboot.model.User getLoggedUser() {
		return userService.findByUsername(((User) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal()).getUsername());
	}
}
