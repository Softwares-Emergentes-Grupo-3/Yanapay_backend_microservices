package agronova.yanapay.iam.application.internal.registerUser;

import agronova.yanapay.iam.domain.models.aggregates.User;
import agronova.yanapay.iam.domain.models.factories.UserFactory;
import agronova.yanapay.iam.domain.services.hashing.IHashingService;
import agronova.yanapay.iam.domain.services.registerUser.IRegisterUserCommandHandler;
import agronova.yanapay.iam.domain.services.registerUser.RegisterUserCommand;
import agronova.yanapay.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import agronova.yanapay.shared.interfaces.exceptions.ConflictException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserCommandHandler implements IRegisterUserCommandHandler {

    private final UserRepository userRepository;
    private final IHashingService hashingService;
    private static final Logger logger = LoggerFactory.getLogger(RegisterUserCommandHandler.class);

    public RegisterUserCommandHandler(UserRepository userRepository, IHashingService hashingService) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
    }

    @Override
    public User handle(RegisterUserCommand command) {
        if (userRepository.existsByEmail(command.email())) {
            logger.warn("Attempt to register user with existing email: {}", command.email());
            throw new ConflictException("User with email " + command.email() + " already exists.");
        }

        User user = UserFactory.createUser(command);

        user.setPassword(hashingService.encode(user.getPassword()));

        logger.info("Registering user with email: {}", command.email());
        return userRepository.save(user);
    }
}
