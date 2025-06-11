package agronova.yanapay.greenhouses.application.internal.updateGreenhouse;

import agronova.yanapay.greenhouses.domain.model.aggregates.Greenhouse;
import agronova.yanapay.greenhouses.domain.model.infrastructure.persistence.jpa.repositories.GreenhouseRepository;
import agronova.yanapay.greenhouses.domain.services.updateGreenhouse.IUpdateGreenhouseCommandHandler;
import agronova.yanapay.greenhouses.domain.services.updateGreenhouse.UpdateGreenhouseCommand;
import agronova.yanapay.shared.interfaces.exceptions.ConflictException;
import agronova.yanapay.shared.interfaces.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateGreenhouseCommandHandler implements IUpdateGreenhouseCommandHandler
{
    private final GreenhouseRepository greenhouseRepository;

    @Autowired
    public UpdateGreenhouseCommandHandler(GreenhouseRepository greenhouseRepository) {
        this.greenhouseRepository = greenhouseRepository;
    }

    @Override
    public Greenhouse handle(UpdateGreenhouseCommand command) {

        var greenhouse = greenhouseRepository.findById(command.id())
                .orElseThrow(() -> new ResourceNotFoundException("Greenhouse not found with id: " + command.id()));

        if (greenhouseRepository.existsByName((command.name()))) {
            throw new ConflictException("Name already exists");
        }

        greenhouse.updateName(command.name());

        greenhouseRepository.save(greenhouse);

        return greenhouse;
    }
}
