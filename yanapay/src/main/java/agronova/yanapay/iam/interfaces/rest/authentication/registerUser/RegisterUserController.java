package agronova.yanapay.iam.interfaces.rest.authentication.registerUser;

import agronova.yanapay.iam.domain.services.registerUser.IRegisterUserCommandHandler;
import agronova.yanapay.iam.domain.services.registerUser.RegisterUserCommand;
import agronova.yanapay.iam.interfaces.rest.authentication.AuthenticationController;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterUserController extends AuthenticationController {
    private final IRegisterUserCommandHandler commandHandler;

    @Autowired
    public RegisterUserController(IRegisterUserCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> registerUser(@Valid @RequestBody RegisterUserRequest request) {
        var command = new RegisterUserCommand(
            request.email(),
            request.password(),
            request.firstName(),
            request.lastName()
        );

        var user = commandHandler.handle(command);

        var response = new RegisterUserResponse(
            user.getId(),
            user.getEmail(),
            user.getProfile().getFirstName(),
            user.getProfile().getLastName()
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
