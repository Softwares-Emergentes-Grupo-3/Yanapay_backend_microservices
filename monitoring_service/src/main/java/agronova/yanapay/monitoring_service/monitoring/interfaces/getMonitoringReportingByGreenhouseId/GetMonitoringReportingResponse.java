package agronova.yanapay.monitoring_service.monitoring.interfaces.getMonitoringReportingByGreenhouseId;

public record GetMonitoringReportingResponse(
        float temperature,
        float humidity
) {
}
