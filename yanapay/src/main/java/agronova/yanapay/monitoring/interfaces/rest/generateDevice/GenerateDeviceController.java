package agronova.yanapay.monitoring.interfaces.rest.generateDevice;

import agronova.yanapay.monitoring.domain.services.generateDevice.GenerateDeviceCommand;
import agronova.yanapay.monitoring.domain.services.generateDevice.IGenerateDeviceCommandHandler;
import agronova.yanapay.monitoring.interfaces.rest.DeviceController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenerateDeviceController extends DeviceController {
    private final IGenerateDeviceCommandHandler generateDeviceCommandHandler;

    @Autowired
    public GenerateDeviceController(IGenerateDeviceCommandHandler generateDeviceCommandHandler) {
        this.generateDeviceCommandHandler = generateDeviceCommandHandler;
    }

    @PostMapping("/generate-device")
    public ResponseEntity<GenerateDeviceResponse> generateDevice() {
        var generateDeviceCommand = new GenerateDeviceCommand();

        var result = generateDeviceCommandHandler.handle(generateDeviceCommand);

        var response = new GenerateDeviceResponse(result);

        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

}
