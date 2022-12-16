package design.boilerplate.springboot.service;

import design.boilerplate.springboot.exceptions.RegistrationException;
import design.boilerplate.springboot.repository.UserRepository;
import design.boilerplate.springboot.security.dto.RegistrationRequest;
import design.boilerplate.springboot.utils.ExceptionMessageAccessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserValidationService {

  private static final String EMAIL_ALREADY_EXISTS = "email_already_exists";
  private static final String CPF_ALREADY_EXISTS = "cpf_already_exists";

  private static final String USERNAME_ALREADY_EXISTS = "username_already_exists";

  private final UserRepository userRepository;

  private final ExceptionMessageAccessor exceptionMessageAccessor;

  public void validateUser(RegistrationRequest registrationRequest) {
    checkEmail(registrationRequest.getEmail());
    checkCpf(registrationRequest.getCpf());
    checkUsername(registrationRequest.getUsername());
  }

  private void checkUsername(String username) {

    final boolean existsByUsername = userRepository.existsByUsername(username);

    if (existsByUsername) {
      log.warn("{} is already being used!", username);

      final String existsUsername = exceptionMessageAccessor.getMessage(null,
          USERNAME_ALREADY_EXISTS);
      throw new RegistrationException(existsUsername);
    }
  }

  private void checkCpf(String cpf) {
    final boolean existsByCpf = userRepository.existsByCpf(cpf);

    if (existsByCpf) {

      log.warn("{} is already being used!", cpf);

      final String existsEmail = exceptionMessageAccessor.getMessage(null, CPF_ALREADY_EXISTS);
      throw new RegistrationException(existsEmail);
    }
  }

  private void checkEmail(String email) {

    final boolean existsByEmail = userRepository.existsByEmail(email);

    if (existsByEmail) {

      log.warn("{} is already being used!", email);

      final String existsEmail = exceptionMessageAccessor.getMessage(null, EMAIL_ALREADY_EXISTS);
      throw new RegistrationException(existsEmail);
    }
  }

}
