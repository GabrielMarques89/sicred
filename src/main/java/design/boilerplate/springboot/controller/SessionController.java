package design.boilerplate.springboot.controller;

import design.boilerplate.springboot.model.dto.RegistrationResponse;
import design.boilerplate.springboot.model.dto.SessionOpenningRequest;
import design.boilerplate.springboot.model.dto.SessionRequest;
import design.boilerplate.springboot.service.SessionService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/session")
public class SessionController {
	private final SessionService sessionService;

	@PostMapping()
	public ResponseEntity<RegistrationResponse> createSession(@Valid @RequestBody SessionRequest sessionRequest) {
		sessionService.createSession(sessionRequest);

		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}

	@PutMapping()
	public ResponseEntity<RegistrationResponse> createSession(@Valid @RequestBody SessionOpenningRequest sessionRequest) {
		sessionService.beginSession(sessionRequest);

		//TODO: Internacionalizar
		return ResponseEntity.status(HttpStatus.CREATED).body(new RegistrationResponse("Votação iniciada com sucesso"));
	}
}
