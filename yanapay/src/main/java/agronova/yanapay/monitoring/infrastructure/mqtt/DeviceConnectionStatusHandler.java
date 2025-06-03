package agronova.yanapay.monitoring.infrastructure.mqtt;

import agronova.yanapay.monitoring.domain.services.updateDeviceConnectionStatus.IUpdateDeviceConnectionStatusCommandHandler;
import agronova.yanapay.monitoring.domain.services.updateDeviceConnectionStatus.UpdateDeviceConnectionStatusCommand;
import agronova.yanapay.shared.domain.services.MqttMessageHandler;
import agronova.yanapay.shared.domain.services.MqttTopicHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@MqttTopicHandler(topic = "yanapay/devices/+/connection_status")
@Component
public class DeviceConnectionStatusHandler implements MqttMessageHandler {

    private final IUpdateDeviceConnectionStatusCommandHandler updateDeviceConnectionStatusCommandHandler;

    @Autowired
    public DeviceConnectionStatusHandler(IUpdateDeviceConnectionStatusCommandHandler updateDeviceConnectionStatusCommandHandler) {
        this.updateDeviceConnectionStatusCommandHandler = updateDeviceConnectionStatusCommandHandler;
    }

    @Override
    public void handle(MqttMessage message) {
        String messageString = new String(message.getPayload());
        System.out.println("Handling device status: " + messageString);

        try {
            var command = new ObjectMapper().readValue(messageString, UpdateDeviceConnectionStatusCommand.class);
            updateDeviceConnectionStatusCommandHandler.handle(command);
        } catch (JsonProcessingException e) {
            System.err.println("Error parsing MQTT message: " + e.getMessage());
        }
    }
}
