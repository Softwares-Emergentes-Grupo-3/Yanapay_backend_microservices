package agronova.yanapay.iam.domain.services.registerUser;

import agronova.yanapay.iam.domain.models.aggregates.User;

public interface IRegisterUserCommandHandler {
    User handle(RegisterUserCommand command);
}
