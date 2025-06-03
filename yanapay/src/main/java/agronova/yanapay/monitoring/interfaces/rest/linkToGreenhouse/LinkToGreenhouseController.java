package agronova.yanapay.monitoring.interfaces.rest.linkToGreenhouse;

import agronova.yanapay.monitoring.domain.services.linkToGreenhouse.ILinkToGreenhouseCommandHandler;
import agronova.yanapay.monitoring.domain.services.linkToGreenhouse.LinkToGreenhouseCommand;
import agronova.yanapay.monitoring.interfaces.rest.LinkingController;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinkToGreenhouseController extends LinkingController {
    private final ILinkToGreenhouseCommandHandler commandHandler;

    @Autowired
    public LinkToGreenhouseController(ILinkToGreenhouseCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @PostMapping("/link")
    public ResponseEntity<LinkToGreenhouseResponse> linkToGreenhouse(@Valid @RequestBody LinkToGreenhouseRequest request) {
        var command = new LinkToGreenhouseCommand(request.deviceCode(), request.greenhouseId());
        var responseMessage = commandHandler.handle(command);

        return ResponseEntity.ok(new LinkToGreenhouseResponse(responseMessage));
    }
}
