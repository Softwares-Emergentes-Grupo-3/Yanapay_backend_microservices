package agronova.yanapay.iam.domain.services.loginUser;

import agronova.yanapay.iam.domain.models.aggregates.User;

public interface ILoginUserCommandHandler {
    User handle(LoginUserCommand command);
}
