package agronova.yanapay.monitoring_service.monitoring.interfaces.getMonitoringReportingByGreenhouseIdAndTimestamp;

import agronova.yanapay.monitoring_service.monitoring.domain.services.getMonitoringReportingByGreenhouseIdAndTimestamp.GetMonitoringReportByGreenhouseIdAndTimestampQuery;
import agronova.yanapay.monitoring_service.monitoring.domain.services.getMonitoringReportingByGreenhouseIdAndTimestamp.IGetMonitoringReportByGreenhouseIdAndTimestampQueryHandler;
import agronova.yanapay.monitoring_service.monitoring.interfaces.MonitoringReportController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetListMonitoringReportingByGreenhouseIdAndTimestampController extends MonitoringReportController {
    private final IGetMonitoringReportByGreenhouseIdAndTimestampQueryHandler getMonitoringReportByGreenhouseIdAndTimestampQueryHandler;

    public GetListMonitoringReportingByGreenhouseIdAndTimestampController(IGetMonitoringReportByGreenhouseIdAndTimestampQueryHandler queryHandler) {
        this.getMonitoringReportByGreenhouseIdAndTimestampQueryHandler = queryHandler;
    }

    @GetMapping("/get-by-greenhouseid-by-seven-ago")
    public ResponseEntity<GetListMonitoringReportingResponse> getByGreenhouseIdAndTimestamp(
            @RequestParam(required = true) Long greenhouseId
    ){
        var query = new GetMonitoringReportByGreenhouseIdAndTimestampQuery(greenhouseId);
        var result = getMonitoringReportByGreenhouseIdAndTimestampQueryHandler.handle(query);

        var response = new GetListMonitoringReportingResponse(result);

        return ResponseEntity.ok(response);
    }
}
