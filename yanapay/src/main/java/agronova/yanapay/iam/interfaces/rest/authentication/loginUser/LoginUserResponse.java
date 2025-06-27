package agronova.yanapay.iam.interfaces.rest.authentication.loginUser;

public record LoginUserResponse(Long userId, String email, String firstName, String lastName) {
}
