package agronova.yanapay.irrigation.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Irrigation", description = "Irrigation management")
@RequestMapping(value = "api/v1/irrigation", produces = MediaType.APPLICATION_JSON_VALUE)
public abstract class IrrigationController {
}
