package agronova.yanapay.greenhouses.application.internal.deleteGreenhouse;

import agronova.yanapay.greenhouses.domain.model.infrastructure.persistence.jpa.repositories.GreenhouseRepository;
import agronova.yanapay.greenhouses.domain.services.deleteGreenhouse.DeleteGreenhouseCommand;
import agronova.yanapay.greenhouses.domain.services.deleteGreenhouse.IDeleteGreenhouseCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteGreenhouseCommandHandler implements IDeleteGreenhouseCommandHandler {
    private final GreenhouseRepository greenhouseRepository;

    @Autowired
    public DeleteGreenhouseCommandHandler(GreenhouseRepository greenhouseRepository) {
        this.greenhouseRepository = greenhouseRepository;
    }

    @Override
    public String handle(DeleteGreenhouseCommand command) {
        // Verify if the Greenhouse exists
        if(greenhouseRepository.existsGreenhouseById(command.id())){
            greenhouseRepository.deleteById(command.id());
            return String.format("greenhouse with id %d deleted", command.id());
        }

        return String.format("greenhouse with id %d not found", command.id());

    }
}
