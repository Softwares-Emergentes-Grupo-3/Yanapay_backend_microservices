package agronova.yanapay.irrigation.interfaces.rest.stopFumigation;

import agronova.yanapay.irrigation.domain.services.stopFumigation.IStopFumigationCommandHandler;
import agronova.yanapay.irrigation.domain.services.stopFumigation.StopFumigationCommand;
import agronova.yanapay.irrigation.interfaces.rest.IrrigationController;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StopFumigationController extends IrrigationController {
    private final IStopFumigationCommandHandler commandHandler;

    public StopFumigationController(IStopFumigationCommandHandler stopFumigationCommandHandler) {
        this.commandHandler = stopFumigationCommandHandler;
    }

    @PostMapping("/stop-fumigation")
    public ResponseEntity<StopFumigationResponse> stopFumigation(@Valid @RequestBody StopFumigationRequest request) {
        var command = new StopFumigationCommand(request.greenhouseId());
        var message = commandHandler.handle(command);
        return ResponseEntity.ok(new StopFumigationResponse(message));
    }
}
