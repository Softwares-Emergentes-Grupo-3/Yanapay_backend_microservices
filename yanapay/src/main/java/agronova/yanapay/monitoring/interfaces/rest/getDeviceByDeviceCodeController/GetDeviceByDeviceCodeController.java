package agronova.yanapay.monitoring.interfaces.rest.getDeviceByDeviceCodeController;

import agronova.yanapay.monitoring.domain.services.getDeviceByDeviceCode.GetDeviceByDeviceCodeQuery;
import agronova.yanapay.monitoring.domain.services.getDeviceByDeviceCode.IGetDeviceByDeviceCodeQueryHandler;
import agronova.yanapay.monitoring.interfaces.rest.DeviceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetDeviceByDeviceCodeController extends DeviceController {
    private final IGetDeviceByDeviceCodeQueryHandler getDeviceByDeviceCodeQueryHandler;

    @Autowired
    public GetDeviceByDeviceCodeController(IGetDeviceByDeviceCodeQueryHandler getDeviceByDeviceCodeQueryHandler) {
        this.getDeviceByDeviceCodeQueryHandler = getDeviceByDeviceCodeQueryHandler;
    }

    @GetMapping("/get-by-code")
    public ResponseEntity<GetDeviceByDeviceCodeResponse> getDeviceByDeviceCode
    (
        @RequestParam(required = true) String deviceCode
    )
    {
        var query = new GetDeviceByDeviceCodeQuery(deviceCode);

        var device = getDeviceByDeviceCodeQueryHandler.handle(query);

        var response = new GetDeviceByDeviceCodeResponse(
                device.getId(),
                device.getDeviceCode(),
                device.isOnline()
        );

        return ResponseEntity.ok(response);
    }
}
