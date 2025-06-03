package agronova.yanapay.shared.application;

import agronova.yanapay.shared.domain.services.IMqttPublisher;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqttPublisher implements IMqttPublisher {
    @Autowired
    private MqttClient client;

    @Override
    public void publish(String topic, String payload) {
        try {
            MqttMessage message = new MqttMessage(payload.getBytes());
            message.setQos(1);
            client.publish(topic, message);
        } catch (MqttException e) {
            throw new RuntimeException("Error publishing MQTT message", e);
        }
    }
}
