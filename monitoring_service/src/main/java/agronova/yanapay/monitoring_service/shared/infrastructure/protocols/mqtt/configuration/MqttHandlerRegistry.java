package agronova.yanapay.monitoring_service.shared.infrastructure.protocols.mqtt.configuration;

import agronova.yanapay.monitoring_service.shared.domain.services.MqttMessageHandler;
import agronova.yanapay.monitoring_service.shared.domain.services.MqttTopicHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class MqttHandlerRegistry {
    private final Map<String, MqttMessageHandler> handlerMap = new HashMap<>();

    @Autowired
    public MqttHandlerRegistry(List<MqttMessageHandler> handlers) {
        for (MqttMessageHandler handler : handlers) {
            MqttTopicHandler annotation = handler.getClass().getAnnotation(MqttTopicHandler.class);
            if (annotation != null) {
                String topic = annotation.topic();
                handlerMap.put(topic, handler);
                System.out.println("Registered handler for topic: " + topic);
            }
        }
    }

    public MqttMessageHandler getHandler(String topic) {
        for (Map.Entry<String, MqttMessageHandler> entry : handlerMap.entrySet()) {
            if (topicMatches(entry.getKey(), topic)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public Set<String> getAllTopics() {
        return handlerMap.keySet();
    }

    private boolean topicMatches(String pattern, String topic) {
        String[] patternParts = pattern.split("/");
        String[] topicParts = topic.split("/");

        if (patternParts.length != topicParts.length) {
            return false;
        }

        for (int i = 0; i < patternParts.length; i++) {
            if (!patternParts[i].equals("+") && !patternParts[i].equals(topicParts[i])) {
                return false;
            }
        }
        return true;
    }
}
