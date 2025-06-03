package agronova.yanapay.shared.domain.services;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MqttTopicHandler {
    String topic();
}
