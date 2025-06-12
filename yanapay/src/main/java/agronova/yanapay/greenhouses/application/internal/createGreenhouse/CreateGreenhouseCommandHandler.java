package agronova.yanapay.greenhouses.application.internal.createGreenhouse;

import agronova.yanapay.greenhouses.domain.model.aggregates.Greenhouse;
import agronova.yanapay.greenhouses.infrastructure.infrastructure.persistence.jpa.repositories.GreenhouseRepository;
import agronova.yanapay.greenhouses.domain.services.createGreenhouse.CreateGreenhouseCommand;
import agronova.yanapay.greenhouses.domain.services.createGreenhouse.ICreateGreenhouseCommandHandler;
import agronova.yanapay.shared.interfaces.exceptions.ConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateGreenhouseCommandHandler implements ICreateGreenhouseCommandHandler {
    private final GreenhouseRepository greenhouseRepository;

    @Autowired
    public CreateGreenhouseCommandHandler(GreenhouseRepository greenhouseRepository) {
        this.greenhouseRepository = greenhouseRepository;
    }

    @Override
    public String handle(CreateGreenhouseCommand command) {
        if (greenhouseRepository.existsByName((command.name()))) {
            throw new ConflictException("Name already exists");
        }

        Greenhouse greenhouse = new Greenhouse(command.name());

        greenhouseRepository.save(greenhouse);

        return greenhouse.getName();
    }
}
