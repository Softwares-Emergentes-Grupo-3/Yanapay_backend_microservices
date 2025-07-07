package agronova.yanapay.iam.interfaces.rest.authentication.registerUser;

public record RegisterUserResponse(
    Long id,
    String email,
    String firstName,
    String lastName
) {
}
