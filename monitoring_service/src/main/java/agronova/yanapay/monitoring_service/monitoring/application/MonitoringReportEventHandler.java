package agronova.yanapay.monitoring_service.monitoring.application;

import agronova.yanapay.monitoring_service.monitoring.domain.services.insertMonitoringReport.IInsertMonitoringReportCommandHandler;
import agronova.yanapay.monitoring_service.monitoring.domain.services.insertMonitoringReport.InsertMonitoringReportCommand;
import agronova.yanapay.monitoring_service.shared.domain.services.MqttMessageHandler;
import agronova.yanapay.monitoring_service.shared.domain.services.MqttTopicHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@MqttTopicHandler(topic = "yanapay/devices/+/monitoring_report")
@Component
public class MonitoringReportEventHandler implements MqttMessageHandler {

    private final IInsertMonitoringReportCommandHandler insertMonitoringReportCommandHandler;
    private static final Logger logger = LoggerFactory.getLogger(MonitoringReportEventHandler.class);

    @Autowired
    public MonitoringReportEventHandler(IInsertMonitoringReportCommandHandler insertMonitoringReportCommandHandler) {
        this.insertMonitoringReportCommandHandler = insertMonitoringReportCommandHandler;
    }

    @Override
    public void handle(MqttMessage message) {
        String messageString;

        try {
            messageString = new String(message.getPayload());
            var command = new ObjectMapper().readValue(messageString, InsertMonitoringReportCommand.class);
            insertMonitoringReportCommandHandler.handle(command);
        }
        catch (JsonProcessingException e) {
            logger.error("Error parsing MQTT message: {}", e.getMessage());
        }
        catch (Exception e) {
            logger.error("An internal error occurred: {}", e.getMessage());
        }

    }
}
