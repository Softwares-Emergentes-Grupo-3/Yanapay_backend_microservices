package agronova.yanapay.monitoring_service.monitoring.interfaces;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "MonitoringReport", description = "MonitoringReport management")
@RequestMapping(value = "api/v1/monitoringreport", produces = MediaType.APPLICATION_JSON_VALUE)

public class MonitoringReportController {
}
