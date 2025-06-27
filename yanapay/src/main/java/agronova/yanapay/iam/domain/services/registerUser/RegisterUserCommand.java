package agronova.yanapay.iam.domain.services.registerUser;

public record RegisterUserCommand(String email, String password, String firstName, String lastName) {
}
