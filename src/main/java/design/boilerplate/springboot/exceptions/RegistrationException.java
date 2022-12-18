package design.boilerplate.springboot.exceptions;

import design.boilerplate.springboot.utils.ExceptionMessageAccessor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class RegistrationException extends RuntimeException {
	private final String errorMessage;
}
