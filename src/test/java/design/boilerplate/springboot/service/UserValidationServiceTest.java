package design.boilerplate.springboot.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import design.boilerplate.springboot.client.CpfApiInterface;
import design.boilerplate.springboot.exceptions.RegistrationException;
import design.boilerplate.springboot.repository.UserRepository;
import design.boilerplate.springboot.utils.ExceptionMessageAccessor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.Test;
import util.BaseSicredTest;
import util.DtoHelper;

public class UserValidationServiceTest extends BaseSicredTest {

  @InjectMocks
  private UserValidationService service;

  @Mock
  private UserRepository userRepository;

  @Mock
  private CpfApiInterface cpfApiInterface;

  @Mock
  private ExceptionMessageAccessor exceptionMessageAccessor;

  @Test(expectedExceptions = RegistrationException.class)
  public void testValidateUserFailInvalidCpf() {
    var req = DtoHelper.mockUserRegistrationRequest();

    when(userRepository.existsByCpf(any())).thenReturn(false);
    when(userRepository.existsByEmail(any())).thenReturn(false);
    when(cpfApiInterface.getPosts(any())).thenReturn(DtoHelper.mockCpfResponse());

    service.validateUser(req);

    verify(userRepository, times(1)).existsByCpf(any());
    verify(userRepository, times(1)).existsByEmail(any());
    verify(cpfApiInterface, times(1)).getPosts(any());
  }

  @Test(expectedExceptions = RegistrationException.class)
  public void testValidateUserFailCpfInUse() {
    var req = DtoHelper.mockUserRegistrationRequest();

    when(userRepository.existsByCpf(any())).thenReturn(true);

    service.validateUser(req);

    verify(userRepository, times(1)).existsByCpf(any());
    verify(userRepository, times(0)).existsByEmail(any());
    verify(cpfApiInterface, times(0)).getPosts(any());
  }

  @Test(expectedExceptions = RegistrationException.class)
  public void testValidateUserFailEmailInUse() {
    var req = DtoHelper.mockUserRegistrationRequest();

    when(userRepository.existsByEmail(any())).thenReturn(true);

    service.validateUser(req);

    verify(userRepository, times(1)).existsByEmail(any());
  }
}