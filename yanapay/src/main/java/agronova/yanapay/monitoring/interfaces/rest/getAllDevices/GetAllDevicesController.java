package agronova.yanapay.monitoring.interfaces.rest.getAllDevices;

import agronova.yanapay.monitoring.domain.services.getAllDevices.GetAllDevicesQuery;
import agronova.yanapay.monitoring.domain.services.getAllDevices.IGetAllDevicesQueryHandler;
import agronova.yanapay.monitoring.interfaces.rest.DeviceController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetAllDevicesController extends DeviceController {
    private final IGetAllDevicesQueryHandler getAllDevicesQueryHandler;

    @Autowired
    public GetAllDevicesController(IGetAllDevicesQueryHandler getAllDevicesQueryHandler) {
        this.getAllDevicesQueryHandler = getAllDevicesQueryHandler;
    }

    @GetMapping()
    public ResponseEntity<GetAllDevicesResponse> getAllDevices() {
        var getAllDevicesQuery = new GetAllDevicesQuery();

        var result = getAllDevicesQueryHandler.handle(getAllDevicesQuery);

        var response = new GetAllDevicesResponse(result);

        return ResponseEntity.ok(response);
    }
}
