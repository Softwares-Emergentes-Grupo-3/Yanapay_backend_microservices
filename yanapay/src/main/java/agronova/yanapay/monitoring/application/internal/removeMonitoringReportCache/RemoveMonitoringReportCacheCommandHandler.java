package agronova.yanapay.monitoring.application.internal.removeMonitoringReportCache;

import agronova.yanapay.monitoring.domain.services.removeMonitoringReportCache.IRemoveMonitoringReportCacheCommandHandler;
import agronova.yanapay.monitoring.domain.services.removeMonitoringReportCache.RemoveMonitoringReportCacheCommand;
import agronova.yanapay.monitoring.infrastructure.persistence.jpa.repositories.MonitoringReportCacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoveMonitoringReportCacheCommandHandler implements IRemoveMonitoringReportCacheCommandHandler {

    private final MonitoringReportCacheRepository monitoringReportCacheRepository;

    @Autowired
    public RemoveMonitoringReportCacheCommandHandler(MonitoringReportCacheRepository monitoringReportCacheRepository) {
        this.monitoringReportCacheRepository = monitoringReportCacheRepository;
    }

    @Override
    public void handle(RemoveMonitoringReportCacheCommand command) {
        monitoringReportCacheRepository.deleteById(command.deviceCode());
    }
}
