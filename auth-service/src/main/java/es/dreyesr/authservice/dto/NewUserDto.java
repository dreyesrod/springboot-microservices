package es.dreyesr.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewUserDto {

  private String firstname;
  private String lastname;
  private String email;
  private String password;
}
