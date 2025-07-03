package agronova.yanapay.monitoring_service.monitoring.interfaces.getMonitoringReportingByGreenhouseIdAndTimestamp;

import agronova.yanapay.monitoring_service.monitoring.domain.model.entities.MonitoringReport;

import java.time.LocalDateTime;
import java.util.List;

public class GetListMonitoringReportingResponse {
    public final List<GetMonitoringReportsByGreenhouseIdAndTimestampDTO> data;

    public GetListMonitoringReportingResponse(List<MonitoringReport> monitoringReports) {
        this.data = monitoringReports.stream()
                .map(monitoringReport -> new GetMonitoringReportsByGreenhouseIdAndTimestampDTO(
                        monitoringReport.getTemperature(),
                        monitoringReport.getHumidity(),
                        monitoringReport.getTimestamp()
                ))
                .toList();
    }
}

record GetMonitoringReportsByGreenhouseIdAndTimestampDTO( float temperature,
                                                          float humidity,
                                                          LocalDateTime timestamp) {
}