package agronova.yanapay.greenhouses.application.internal.getGreenhousesByUser;

import agronova.yanapay.greenhouses.domain.model.aggregates.Greenhouse;
import agronova.yanapay.greenhouses.domain.services.getGreenhousesByUser.GetGreenhousesByUserIdQuery;
import agronova.yanapay.greenhouses.domain.services.getGreenhousesByUser.IGetGreenhousesByUserIdQueryHandler;
import agronova.yanapay.greenhouses.infrastructure.infrastructure.persistence.jpa.repositories.GreenhouseRepository;
import agronova.yanapay.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import agronova.yanapay.shared.interfaces.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetGreenhousesByUserIdQueryHandler implements IGetGreenhousesByUserIdQueryHandler {

    private final GreenhouseRepository greenhouseRepository;
    private final UserRepository userRepository;

    public GetGreenhousesByUserIdQueryHandler(GreenhouseRepository greenhouseRepository, UserRepository userRepository) {
        this.greenhouseRepository = greenhouseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Greenhouse> handle(GetGreenhousesByUserIdQuery query) {
        if (!userRepository.existsById(query.userId())) {
            throw new ResourceNotFoundException("User with id " + query.userId() + " not found");
        }

        var greenhouses = greenhouseRepository.findByUser_Id(query.userId());

        if (greenhouses.isEmpty()) {
            throw new ResourceNotFoundException("No greenhouses found for user with id " + query.userId());
        }

        return greenhouses;
    }
}
