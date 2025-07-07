package agronova.yanapay.irrigation.interfaces.rest.stopWatering;

import agronova.yanapay.irrigation.domain.services.stopWatering.IStopWateringCommandHandler;
import agronova.yanapay.irrigation.domain.services.stopWatering.StopWateringCommand;
import agronova.yanapay.irrigation.interfaces.rest.IrrigationController;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StopWateringController extends IrrigationController {
    private final IStopWateringCommandHandler commandHandler;

    @Autowired
    public StopWateringController(IStopWateringCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @PostMapping("/stop-watering")
    public ResponseEntity<StopWateringResponse> stopWatering(@Valid @RequestBody StopWateringRequest request) {
        var command = new StopWateringCommand(request.greenhouseId());

        String responseMessage = commandHandler.handle(command);

        return ResponseEntity.ok(new StopWateringResponse(responseMessage));
    }
}
