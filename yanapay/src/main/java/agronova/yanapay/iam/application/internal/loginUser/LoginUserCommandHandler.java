package agronova.yanapay.iam.application.internal.loginUser;

import agronova.yanapay.iam.domain.models.aggregates.User;
import agronova.yanapay.iam.domain.services.hashing.IHashingService;
import agronova.yanapay.iam.domain.services.loginUser.ILoginUserCommandHandler;
import agronova.yanapay.iam.domain.services.loginUser.LoginUserCommand;
import agronova.yanapay.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import agronova.yanapay.shared.interfaces.exceptions.UnauthorizedException;
import org.springframework.stereotype.Service;

@Service
public class LoginUserCommandHandler implements ILoginUserCommandHandler {

    private final UserRepository userRepository;
    private final IHashingService hashingService;

    public LoginUserCommandHandler(UserRepository userRepository, IHashingService hashingService) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
    }

    @Override
    public User handle(LoginUserCommand command) {
        User user = userRepository.findByEmail(command.email())
                .orElseThrow(() -> new UnauthorizedException("Email is not registered or password is incorrect."));

        if (!hashingService.matches(command.password(), user.getPassword())) {
            throw new UnauthorizedException("Email is not registered or password is incorrect.");
        }

        return user;
    }
}
