package design.boilerplate.springboot.controller;

import design.boilerplate.springboot.security.dto.RegistrationResponse;
import design.boilerplate.springboot.security.dto.SessionOpenningRequest;
import design.boilerplate.springboot.security.dto.SessionRequest;
import design.boilerplate.springboot.security.service.SessionService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

	//TODO: iniciar votação (campo opcional > Duração)
	//Ao iniciar a votação, são aceitos votos.
	//Ao iniciar a votação, o campo duração (minutos) é um parâmetro opcional, caso exista, define o valor da data final de votação.
	//Ao final da duração, não são aceitos mais votos.
	@PutMapping()
	public ResponseEntity<RegistrationResponse> createSession(@Valid @RequestBody SessionOpenningRequest sessionRequest) {
		sessionService.beginSession(sessionRequest);

		//TODO: Internacionalizar
		return ResponseEntity.status(HttpStatus.CREATED).body(new RegistrationResponse("Votação iniciada com sucesso"));
	}
}
