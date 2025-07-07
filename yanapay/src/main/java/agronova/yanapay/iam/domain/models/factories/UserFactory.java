package agronova.yanapay.iam.domain.models.factories;

import agronova.yanapay.iam.domain.models.aggregates.User;
import agronova.yanapay.iam.domain.models.entities.Profile;
import agronova.yanapay.iam.domain.services.registerUser.RegisterUserCommand;

public class UserFactory {
    public static User createUser(RegisterUserCommand command) {
        Profile profile = new Profile(
                command.firstName(),
            command.lastName()
        );

        return new User(
            command.email(),
            command.password(),
            profile
        );
    }
}
