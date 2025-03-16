package manugtx.jwtexample.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String authority;
}
