package agronova.yanapay.greenhouses.interfaces.getDevicesByGreenhouseId;

import agronova.yanapay.greenhouses.domain.services.getDevicesByGreenhouseId.GetDevicesByGreenhouseIdQuery;
import agronova.yanapay.greenhouses.domain.services.getDevicesByGreenhouseId.IGetDevicesByGreenhouseIdQueryHandler;
import agronova.yanapay.greenhouses.interfaces.GreenhouseController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetDevicesByGreenhouseIdController extends GreenhouseController {
    private final IGetDevicesByGreenhouseIdQueryHandler queryHandler;

    public GetDevicesByGreenhouseIdController(IGetDevicesByGreenhouseIdQueryHandler queryHandler) {
        this.queryHandler = queryHandler;
    }

    @GetMapping("{greenhouseId}/devices")
    public ResponseEntity<GetDevicesByGreenhouseIdResponse> getDevicesByGreenhouseId(@PathVariable Long greenhouseId) {
        var query = new GetDevicesByGreenhouseIdQuery(greenhouseId);
        var result = queryHandler.handle(query);

        var response = new GetDevicesByGreenhouseIdResponse(result);

        return ResponseEntity.ok(response);
    }
}
