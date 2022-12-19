package design.boilerplate.springboot.service;

import design.boilerplate.springboot.model.User;
import design.boilerplate.springboot.model.dto.AuthenticatedUserDto;
import design.boilerplate.springboot.model.dto.RegistrationResponse;
import design.boilerplate.springboot.model.dto.UserRegistrationRequest;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;


public interface UserService {

  User findByUsername(String username);

  RegistrationResponse registration(UserRegistrationRequest registrationRequest);

  AuthenticatedUserDto findAuthenticatedUserByUsername(String username);

  Long countUsers();

  RegistrationResponse registrationV2(UserRegistrationRequest registrationRequest)
      throws NotImplementedException;

}
