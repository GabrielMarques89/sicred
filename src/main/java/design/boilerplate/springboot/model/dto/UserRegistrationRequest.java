package design.boilerplate.springboot.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserRegistrationRequest {

  @NotEmpty(message = "{registration_name_not_empty}")
  private String name;

  @Email(message = "{registration_email_is_not_valid}")
  @NotEmpty(message = "{registration_email_not_empty}")
  private String email;

  @NotEmpty(message = "{registration_username_not_empty}")
  private String username;

  @NotEmpty(message = "{registration_username_not_empty}")
  private String cpf;

  @NotEmpty(message = "{registration_password_not_empty}")
  private String password;

}
