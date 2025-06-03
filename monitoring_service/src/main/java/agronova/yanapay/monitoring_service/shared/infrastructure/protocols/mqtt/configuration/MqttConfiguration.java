package agronova.yanapay.monitoring_service.shared.infrastructure.protocols.mqtt.configuration;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttConfiguration {
    @Value("${mqtt.broker}")
    private String mqttBroker;
    @Value("${mqtt.clientId}")
    private String mqttClientId;
    @Value("${mqtt.username}")
    private String mqttUsername;
    @Value("${mqtt.password}")
    private String mqttPassword;

    private final MqttCallbackHandler callbackHandler;
    private final MqttHandlerRegistry handlerRegistry;

    public MqttConfiguration(MqttCallbackHandler callbackHandler, MqttHandlerRegistry handlerRegistry) {
        this.callbackHandler = callbackHandler;
        this.handlerRegistry = handlerRegistry;
    }

    @Bean
    public MqttClient mqttClient() throws MqttException {
        MqttClient client = new MqttClient(mqttBroker, mqttClientId, new MemoryPersistence());

        MqttConnectOptions options = buildMqttOptions();
        client.connect(options);

        client.setCallback(callbackHandler);

        for (String topic : handlerRegistry.getAllTopics()) {
            client.subscribe(topic);
        }

        return client;
    }

    private MqttConnectOptions buildMqttOptions() {
        MqttConnectOptions options = new MqttConnectOptions();

        options.setCleanSession(false);
        options.setAutomaticReconnect(true);
        options.setUserName(mqttUsername);
        options.setPassword(mqttPassword.toCharArray());

        return options;
    }
}
