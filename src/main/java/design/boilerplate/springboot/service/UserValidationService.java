package design.boilerplate.springboot.service;

import design.boilerplate.springboot.client.CpfApiInterface;
import design.boilerplate.springboot.exceptions.RegistrationException;
import design.boilerplate.springboot.repository.UserRepository;
import design.boilerplate.springboot.security.dto.RegistrationRequest;
import design.boilerplate.springboot.utils.ExceptionMessageAccessor;
import feign.Feign;
import feign.gson.GsonDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserValidationService {

  private static final String EMAIL_ALREADY_EXISTS = "email_already_exists";
  private static final String CPF_ALREADY_EXISTS = "cpf_already_exists";
  private static final String INVALID_CPF = "invalid_cpf";

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

    var client =  Feign.builder()
        .decoder(new GsonDecoder())
        .target(CpfApiInterface.class, "https://api.cpfcnpj.com.br/5ae973d7a997af13f0aaf2bf60e65803/1/");

    var result = client.getPosts(cpf);
    if(!result.isValidCpf()){
      log.warn("Cpf {} is invalid!", cpf);

      final String existsEmail = exceptionMessageAccessor.getMessage(null, INVALID_CPF, cpf);
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
