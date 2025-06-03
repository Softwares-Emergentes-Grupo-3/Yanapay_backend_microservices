package agronova.yanapay.shared.domain.services;

public interface IMqttPublisher {
    void publish(String topic, String payload);
}
