package agronova.yanapay.shared.domain.services;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public interface MqttMessageHandler {
    void handle(MqttMessage message);
}
