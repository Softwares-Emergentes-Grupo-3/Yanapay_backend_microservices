package agronova.yanapay.monitoring_service.monitoring.interfaces.getMonitoringReportingByGreenhouseId;

import agronova.yanapay.monitoring_service.monitoring.domain.model.entities.MonitoringReport;
import agronova.yanapay.monitoring_service.monitoring.domain.services.getMonitoringReportingByGreenhouseId.GetMonitoringReportingByGreenhouseIdQuery;
import agronova.yanapay.monitoring_service.monitoring.domain.services.getMonitoringReportingByGreenhouseId.IGetMonitoringReportingByGreenhouseIdQueryHandler;
import agronova.yanapay.monitoring_service.monitoring.interfaces.MonitoringReportController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class GetMonitoringReportingByGreenhouseIdController extends MonitoringReportController {
    private final IGetMonitoringReportingByGreenhouseIdQueryHandler getMonitoringReportingByGreenhouseIdQueryHandler;

    @Autowired
    public GetMonitoringReportingByGreenhouseIdController(IGetMonitoringReportingByGreenhouseIdQueryHandler getMonitoringReportingByGreenhouseIdQueryHandler) {
        this.getMonitoringReportingByGreenhouseIdQueryHandler = getMonitoringReportingByGreenhouseIdQueryHandler;
    }

    @GetMapping("/get-by-greenhouseid")
    public ResponseEntity<GetMonitoringReportingResponse> getMonitoringReportingByGreenhouseId
            (
                    @RequestParam(required = true) Long id
            )
    {
        var query = new GetMonitoringReportingByGreenhouseIdQuery(id);

        MonitoringReport monitoringReport = getMonitoringReportingByGreenhouseIdQueryHandler.handle(query);

        var response = new GetMonitoringReportingResponse(monitoringReport.getTemperature(), monitoringReport.getHumidity());

        return ResponseEntity.ok(response);
    }
}
