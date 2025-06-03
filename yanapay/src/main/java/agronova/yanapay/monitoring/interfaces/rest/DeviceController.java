package agronova.yanapay.monitoring.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Devices", description = "Devices management")
@RequestMapping(value = "api/v1/devices", produces = MediaType.APPLICATION_JSON_VALUE)
public abstract class DeviceController {
}
