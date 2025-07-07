package agronova.yanapay.iam.interfaces.rest.authentication.loginUser;

import agronova.yanapay.iam.domain.services.loginUser.ILoginUserCommandHandler;
import agronova.yanapay.iam.domain.services.loginUser.LoginUserCommand;
import agronova.yanapay.iam.interfaces.rest.authentication.AuthenticationController;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginUserController extends AuthenticationController {
    private final ILoginUserCommandHandler loginUserCommandHandler;

    @Autowired
    public LoginUserController(ILoginUserCommandHandler loginUserCommandHandler) {
        this.loginUserCommandHandler = loginUserCommandHandler;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserResponse> loginUser(@Valid @RequestBody LoginUserRequest request) {
        var command = new LoginUserCommand(request.email(), request.password());
        var user = loginUserCommandHandler.handle(command);

        var response = new LoginUserResponse(
            user.getId(),
            user.getEmail(),
            user.getProfile().getFirstName(),
            user.getProfile().getLastName()
        );

        return ResponseEntity.ok(response);
    }
}
