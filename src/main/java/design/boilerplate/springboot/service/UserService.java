package design.boilerplate.springboot.service;

import design.boilerplate.springboot.model.User;
import design.boilerplate.springboot.model.dto.AuthenticatedUserDto;
import design.boilerplate.springboot.model.dto.RegistrationRequest;
import design.boilerplate.springboot.model.dto.RegistrationResponse;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;


public interface UserService {

	User findByUsername(String username);

	RegistrationResponse registration(RegistrationRequest registrationRequest);

	AuthenticatedUserDto findAuthenticatedUserByUsername(String username);

	Long countUsers();
	RegistrationResponse registrationV2(RegistrationRequest registrationRequest)
			throws NotImplementedException;

}
