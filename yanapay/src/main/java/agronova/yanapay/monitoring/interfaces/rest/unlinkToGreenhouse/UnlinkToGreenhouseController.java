package agronova.yanapay.monitoring.interfaces.rest.unlinkToGreenhouse;

import agronova.yanapay.monitoring.domain.services.unlinkToGreenhouse.IUnlinkToGreenhouseCommandHandler;
import agronova.yanapay.monitoring.domain.services.unlinkToGreenhouse.UnlinkToGreenhouseCommand;
import agronova.yanapay.monitoring.interfaces.rest.LinkingController;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UnlinkToGreenhouseController extends LinkingController {

    private final IUnlinkToGreenhouseCommandHandler commandHandler;

    @Autowired
    public UnlinkToGreenhouseController(IUnlinkToGreenhouseCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @PostMapping("/unlink")
    public ResponseEntity<UnlinkToGreenhouseResponse> unlinkToGreenhouse(@Valid @RequestBody UnlinkToGreenhouseRequest request) {
        var command = new UnlinkToGreenhouseCommand(request.deviceCode());
        String responseMessage = commandHandler.handle(command);

        return ResponseEntity.ok(new UnlinkToGreenhouseResponse(responseMessage));
    }
}
