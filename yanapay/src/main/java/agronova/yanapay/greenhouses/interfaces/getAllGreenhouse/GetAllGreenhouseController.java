package agronova.yanapay.greenhouses.interfaces.getAllGreenhouse;

import agronova.yanapay.greenhouses.domain.services.getAllGreenhouse.GetAllGreenhouseQuery;
import agronova.yanapay.greenhouses.domain.services.getAllGreenhouse.IGetAllGreenhouseQueryHandler;
import agronova.yanapay.greenhouses.interfaces.GreenhouseController;
import agronova.yanapay.monitoring.domain.services.getAllDevices.GetAllDevicesQuery;
import agronova.yanapay.monitoring.domain.services.getAllDevices.IGetAllDevicesQueryHandler;
import agronova.yanapay.monitoring.interfaces.rest.getAllDevices.GetAllDevicesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetAllGreenhouseController extends GreenhouseController {
    private final IGetAllGreenhouseQueryHandler getAllGreenhouseQueryHandler;

    @Autowired
    public GetAllGreenhouseController(IGetAllGreenhouseQueryHandler getAllGreenhouseQueryHandler) {
        this.getAllGreenhouseQueryHandler = getAllGreenhouseQueryHandler;
    }

    @GetMapping()
    public ResponseEntity<List<GetAllGreenhouseResponse>> getAllGreenhouse() {
        var getAllGreenhouseQuery = new GetAllGreenhouseQuery();

        var result = getAllGreenhouseQueryHandler.handle(getAllGreenhouseQuery);

        var response = result.stream()
                .map(x ->
                        new GetAllGreenhouseResponse(
                                x.getId(),
                                x.getName()
                        )
                )
                .toList();

        return ResponseEntity.ok(response);
    }

}
