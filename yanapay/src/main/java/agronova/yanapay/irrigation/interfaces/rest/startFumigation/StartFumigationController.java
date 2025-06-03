package agronova.yanapay.irrigation.interfaces.rest.startFumigation;

import agronova.yanapay.irrigation.domain.services.startFumigation.IStartFumigationCommandHandler;
import agronova.yanapay.irrigation.domain.services.startFumigation.StartFumigationCommand;
import agronova.yanapay.irrigation.interfaces.rest.IrrigationController;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StartFumigationController extends IrrigationController {
    private final IStartFumigationCommandHandler commandHandler;

    @Autowired
    public StartFumigationController(IStartFumigationCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @PostMapping("/start-fumigation")
    public ResponseEntity<StartFumigationResponse> startFumigation(@Valid @RequestBody StartFumigationRequest request) {
        var command = new StartFumigationCommand(request.greenhouseId());

        String responseMessage = commandHandler.handle(command);

        return ResponseEntity.ok(new StartFumigationResponse(responseMessage));
    }
}
