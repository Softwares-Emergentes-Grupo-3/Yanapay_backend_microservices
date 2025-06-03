package agronova.yanapay.monitoring.application.internal.insertMonitoringReportCache;

import agronova.yanapay.monitoring.domain.model.entities.MonitoringReportCache;
import agronova.yanapay.monitoring.domain.services.insertMonitoringReportCache.IInsertMonitoringReportCacheCommandHandler;
import agronova.yanapay.monitoring.domain.services.insertMonitoringReportCache.InsertMonitoringReportCacheCommand;
import agronova.yanapay.monitoring.infrastructure.persistence.jpa.repositories.MonitoringReportCacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsertMonitoringReportCacheCommandHandler implements IInsertMonitoringReportCacheCommandHandler {

    private final MonitoringReportCacheRepository monitoringReportCacheRepository;

    @Autowired
    public InsertMonitoringReportCacheCommandHandler(MonitoringReportCacheRepository monitoringReportCacheRepository) {
        this.monitoringReportCacheRepository = monitoringReportCacheRepository;
    }

    @Override
    public void handle(InsertMonitoringReportCacheCommand command) {

        MonitoringReportCache monitoringReportCache = new MonitoringReportCache(
                command.deviceCode(),
                command.greenhouseId()
        );

        monitoringReportCacheRepository.save(monitoringReportCache);
    }
}

