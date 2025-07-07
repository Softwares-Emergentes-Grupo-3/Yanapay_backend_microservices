package agronova.yanapay.iam.interfaces.rest.authentication.registerUser;

import jakarta.validation.constraints.NotBlank;

public record RegisterUserRequest(
    @NotBlank(message = "Email cannot be blank")
    String email,
    @NotBlank(message = "Password cannot be blank")
    String password,
    @NotBlank(message = "First name cannot be blank")
    String firstName,
    @NotBlank(message = "Lastname cannot be blank")
    String lastName
) {
}
