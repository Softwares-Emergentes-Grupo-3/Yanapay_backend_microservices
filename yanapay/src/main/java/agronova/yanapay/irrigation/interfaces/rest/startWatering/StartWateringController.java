package agronova.yanapay.irrigation.interfaces.rest.startWatering;

import agronova.yanapay.irrigation.domain.services.startWatering.IStartWateringCommandHandler;
import agronova.yanapay.irrigation.domain.services.startWatering.StartWateringCommand;
import agronova.yanapay.irrigation.interfaces.rest.IrrigationController;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StartWateringController extends IrrigationController {
    private final IStartWateringCommandHandler commandHandler;

    @Autowired
    public StartWateringController(IStartWateringCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @PostMapping("/start-watering")
    public ResponseEntity<StartWateringResponse> startWatering(@Valid @RequestBody StartWateringRequest request) {
        var command = new StartWateringCommand(request.greenhouseId());

        String responseMessage = commandHandler.handle(command);

        return ResponseEntity.ok(new StartWateringResponse(responseMessage));
    }
}
