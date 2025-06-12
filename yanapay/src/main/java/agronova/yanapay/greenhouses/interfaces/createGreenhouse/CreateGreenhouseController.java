package agronova.yanapay.greenhouses.interfaces.createGreenhouse;

import agronova.yanapay.greenhouses.domain.services.createGreenhouse.CreateGreenhouseCommand;
import agronova.yanapay.greenhouses.domain.services.createGreenhouse.ICreateGreenhouseCommandHandler;
import agronova.yanapay.greenhouses.interfaces.GreenhouseController;
import agronova.yanapay.monitoring.domain.services.createDevice.ICreateDeviceCommandHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateGreenhouseController extends GreenhouseController {
    private final ICreateGreenhouseCommandHandler createGreenhouseCommandHandler;

    @Autowired
    public CreateGreenhouseController(ICreateGreenhouseCommandHandler createGreenhouseCommandHandler) {
        this.createGreenhouseCommandHandler = createGreenhouseCommandHandler;
    }

    @PostMapping("/create-greenhouse")
    public ResponseEntity<CreateGreenhouseResponse> createGreenhouse(@Valid @RequestBody CreateGreenhouseRequest request) {
        var createGreenhouseCommand = new CreateGreenhouseCommand(request.name());

        var result = createGreenhouseCommandHandler.handle(createGreenhouseCommand);

        var response = new CreateGreenhouseResponse(result);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
