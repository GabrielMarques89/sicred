package design.boilerplate.springboot.service;

import design.boilerplate.springboot.model.User;
import design.boilerplate.springboot.model.UserRole;
import design.boilerplate.springboot.model.dto.AuthenticatedUserDto;
import design.boilerplate.springboot.model.dto.RegistrationResponse;
import design.boilerplate.springboot.model.dto.UserRegistrationRequest;
import design.boilerplate.springboot.model.mapper.UserMapper;
import design.boilerplate.springboot.repository.UserRepository;
import design.boilerplate.springboot.utils.GeneralMessageAccessor;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private static final String REGISTRATION_SUCCESSFUL = "registration_successful";

  private final UserRepository userRepository;

  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  private final UserValidationService userValidationService;

  private final GeneralMessageAccessor generalMessageAccessor;

  @Override
  public User findByUsername(String username) {

    return userRepository.findByUsername(username);
  }

  @Override
  public Long countUsers() {
    return userRepository.count();
  }

  @Override
  public RegistrationResponse registrationV2(UserRegistrationRequest registrationRequest)
      throws NotImplementedException {
    //TODO: Implementar lógica adicional
    throw new NotImplementedException("Yet to implement");
  }


  public RegistrationResponse registration(UserRegistrationRequest registrationRequest) {

    userValidationService.validateUser(registrationRequest);

    final User user = UserMapper.INSTANCE.map(registrationRequest);
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    user.setUserRole(UserRole.USER);
    userRepository.save(user);

    final String username = registrationRequest.getUsername();
    final String registrationSuccessMessage = generalMessageAccessor.getMessage(REGISTRATION_SUCCESSFUL, username);

    log.info("{} registered successfully!", username);

    return new RegistrationResponse(registrationSuccessMessage);
  }

  @Override
  public AuthenticatedUserDto findAuthenticatedUserByUsername(String username) {

    final User user = findByUsername(username);

    return UserMapper.INSTANCE.convertToAuthenticatedUserDto(user);
  }
}
