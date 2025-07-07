package agronova.yanapay.monitoring.interfaces.rest.createDevice;

import agronova.yanapay.monitoring.domain.services.createDevice.CreateDeviceCommand;
import agronova.yanapay.monitoring.domain.services.createDevice.ICreateDeviceCommandHandler;
import agronova.yanapay.monitoring.interfaces.rest.DeviceController;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateDeviceController extends DeviceController {

    private final ICreateDeviceCommandHandler createDeviceCommandHandler;

    @Autowired
    public CreateDeviceController(ICreateDeviceCommandHandler createDeviceCommandHandler) {
        this.createDeviceCommandHandler = createDeviceCommandHandler;
    }

    @PostMapping("/create-device")
    public ResponseEntity<CreateDeviceResponse> createDevice(@Valid @RequestBody CreateDeviceRequest request) {
        var createDeviceCommand = new CreateDeviceCommand(request.deviceCode());

        var result = createDeviceCommandHandler.handle(createDeviceCommand);

        var response = new CreateDeviceResponse(result);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
