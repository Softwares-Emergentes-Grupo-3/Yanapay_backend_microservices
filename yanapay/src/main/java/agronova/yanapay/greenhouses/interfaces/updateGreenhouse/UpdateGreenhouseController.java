package agronova.yanapay.greenhouses.interfaces.updateGreenhouse;

import agronova.yanapay.greenhouses.domain.services.updateGreenhouse.IUpdateGreenhouseCommandHandler;
import agronova.yanapay.greenhouses.domain.services.updateGreenhouse.UpdateGreenhouseCommand;
import agronova.yanapay.greenhouses.interfaces.GreenhouseController;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateGreenhouseController extends GreenhouseController {
    private final IUpdateGreenhouseCommandHandler updateGreenhouseCommandHandler;

    @Autowired
    public UpdateGreenhouseController(IUpdateGreenhouseCommandHandler updateGreenhouseCommandHandler) {
        this.updateGreenhouseCommandHandler = updateGreenhouseCommandHandler;
    }

    @PutMapping()
    public ResponseEntity<UpdateGreenhouseResponse> updateGreenhouse(@Valid @RequestBody UpdateGreenhouseRequest request) {
        var updateGreenhouseCommand = new UpdateGreenhouseCommand(request.id() ,request.name());

        var result = updateGreenhouseCommandHandler.handle(updateGreenhouseCommand);

        var response = new UpdateGreenhouseResponse(result.getId(), result.getName());

        return ResponseEntity.ok(response);
    }
}
