package agronova.yanapay.greenhouses.interfaces.getGreenhouseById;

import agronova.yanapay.greenhouses.domain.model.aggregates.Greenhouse;
import agronova.yanapay.greenhouses.domain.services.getGreenhouseById.GetGreenhouseByIdQuery;
import agronova.yanapay.greenhouses.domain.services.getGreenhouseById.IGetGreenhouseByIdQueryHandler;
import agronova.yanapay.greenhouses.interfaces.GreenhouseController;
import agronova.yanapay.monitoring.domain.model.aggregates.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class GetGreenhouseByIdController extends GreenhouseController {
    private final IGetGreenhouseByIdQueryHandler getGreenhouseByIdQueryHandler;

    @Autowired
    public GetGreenhouseByIdController(IGetGreenhouseByIdQueryHandler getGreenhouseByIdQueryHandler) {
        this.getGreenhouseByIdQueryHandler = getGreenhouseByIdQueryHandler;
    }

    @GetMapping("/get-by-id")
    public ResponseEntity<GetGreenhouseByIdResponse> getGreenhouseById
            (
                    @RequestParam(required = true) Long id
            )
    {
        var query = new GetGreenhouseByIdQuery(id);

        Greenhouse greenhouse = getGreenhouseByIdQueryHandler.handle(query);
        Set<Device> devices = greenhouse.getDevices();

        List<DeviceDTO> deviceDtos = devices.stream()
                .map(DeviceDTO::fromEntity)
                .toList();

        var response = new GetGreenhouseByIdResponse(greenhouse.getId(), greenhouse.getName(),deviceDtos);

        return ResponseEntity.ok(response);
    }
}
