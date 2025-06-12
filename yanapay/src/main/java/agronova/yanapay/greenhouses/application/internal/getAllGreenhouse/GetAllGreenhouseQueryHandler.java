package agronova.yanapay.greenhouses.application.internal.getAllGreenhouse;

import agronova.yanapay.greenhouses.domain.model.aggregates.Greenhouse;
import agronova.yanapay.greenhouses.infrastructure.infrastructure.persistence.jpa.repositories.GreenhouseRepository;
import agronova.yanapay.greenhouses.domain.services.getAllGreenhouse.GetAllGreenhouseQuery;
import agronova.yanapay.greenhouses.domain.services.getAllGreenhouse.IGetAllGreenhouseQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllGreenhouseQueryHandler implements IGetAllGreenhouseQueryHandler {

    private final GreenhouseRepository greenhouseRepository;

    @Autowired
    public GetAllGreenhouseQueryHandler(GreenhouseRepository greenhouseRepository) {
        this.greenhouseRepository = greenhouseRepository;
    }

    @Override
    public List<Greenhouse> handle(GetAllGreenhouseQuery query) {
        return greenhouseRepository.findAll();
    }

}
