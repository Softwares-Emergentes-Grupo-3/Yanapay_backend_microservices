package agronova.yanapay.greenhouses.application.internal.createGreenhouse;

import agronova.yanapay.greenhouses.domain.model.aggregates.Greenhouse;
import agronova.yanapay.greenhouses.infrastructure.infrastructure.persistence.jpa.repositories.GreenhouseRepository;
import agronova.yanapay.greenhouses.domain.services.createGreenhouse.CreateGreenhouseCommand;
import agronova.yanapay.greenhouses.domain.services.createGreenhouse.ICreateGreenhouseCommandHandler;
import agronova.yanapay.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import agronova.yanapay.shared.interfaces.exceptions.ConflictException;
import agronova.yanapay.shared.interfaces.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateGreenhouseCommandHandler implements ICreateGreenhouseCommandHandler {
    private final GreenhouseRepository greenhouseRepository;
    private final UserRepository userRepository;

    @Autowired
    public CreateGreenhouseCommandHandler(GreenhouseRepository greenhouseRepository, UserRepository userRepository) {
        this.greenhouseRepository = greenhouseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Greenhouse handle(CreateGreenhouseCommand command) {

        var user = userRepository.findById(command.userId())
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + command.userId() + " not found"));

        if (greenhouseRepository.existsByNameAndUser_Id(command.name(), user.getId())) {
            throw new ConflictException("Greenhouse with this name already exists for this user");
        }

        Greenhouse greenhouse = new Greenhouse(command.name(), command.plantingDate(), user);

        greenhouseRepository.save(greenhouse);

        return greenhouse;
    }
}
