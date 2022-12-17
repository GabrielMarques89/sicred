package design.boilerplate.springboot.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CpfResponse {

  public static final String INVALID_STATUS = "0";
  private String status;
  private String cpf;
  private String nome;
  private String pacoteUsado;
  private String saldo;
  private String consultaID;
  private String delay;

  public boolean isValidCpf(){
    return !INVALID_STATUS.equals(status);
  }
}
