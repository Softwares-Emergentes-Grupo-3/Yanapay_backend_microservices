package agronova.yanapay.greenhouses.interfaces;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Greenhouses", description = "Greenhouses management")
@RequestMapping(value = "api/v1/greenhouses", produces = MediaType.APPLICATION_JSON_VALUE)
public class GreenhouseController {
}
