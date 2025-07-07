package agronova.yanapay.greenhouses.interfaces.deleteGreenhouse;

import agronova.yanapay.greenhouses.domain.services.deleteGreenhouse.DeleteGreenhouseCommand;
import agronova.yanapay.greenhouses.domain.services.deleteGreenhouse.IDeleteGreenhouseCommandHandler;
import agronova.yanapay.greenhouses.interfaces.GreenhouseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DeleteGreenhouseController extends GreenhouseController {

    private final IDeleteGreenhouseCommandHandler commandHandler;

    @Autowired
    public DeleteGreenhouseController(IDeleteGreenhouseCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteGreenhouseResponse> deleteGreenhouse(@PathVariable Long id) {
        var command = new DeleteGreenhouseCommand(id);
        String responseMessage = commandHandler.handle(command);

        return ResponseEntity.ok(new DeleteGreenhouseResponse(responseMessage));

    }
}
