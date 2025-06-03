package agronova.yanapay.shared.infrastructure.protocols.mqtt.configuration;

import agronova.yanapay.shared.domain.services.MqttMessageHandler;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MqttCallbackHandler implements MqttCallback {
    private final MqttHandlerRegistry registry;
    private static final Logger logger = LoggerFactory.getLogger(MqttCallbackHandler.class);

    public MqttCallbackHandler(MqttHandlerRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void connectionLost(Throwable cause) {
        logger.error("connectionLost: {}", cause.getMessage());
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
        logger.info("Message arrived on topic: {}", topic);

        try {
            MqttMessageHandler handler = registry.getHandler(topic);
            if (handler != null) {
                handler.handle(message);
            } else {
                logger.warn("No handler found for topic: {}", topic);
            }
        } catch (Exception e) {
            logger.error("Error processing message on topic {}: {}", topic, e.getMessage());
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        logger.info("Delivery completed - Token Id: {}", token.getMessageId());
    }
}
