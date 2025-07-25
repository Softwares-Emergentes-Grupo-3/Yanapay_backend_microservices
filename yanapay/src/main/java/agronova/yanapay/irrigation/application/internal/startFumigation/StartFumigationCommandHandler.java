package agronova.yanapay.irrigation.application.internal.startFumigation;

import agronova.yanapay.greenhouses.infrastructure.infrastructure.persistence.jpa.repositories.GreenhouseRepository;
import agronova.yanapay.irrigation.domain.models.factories.IrrigationFactory;
import agronova.yanapay.irrigation.domain.services.startFumigation.IStartFumigationCommandHandler;
import agronova.yanapay.irrigation.domain.services.startFumigation.StartFumigationCommand;
import agronova.yanapay.shared.application.MqttPublisher;
import agronova.yanapay.shared.interfaces.exceptions.ResourceNotFoundException;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StartFumigationCommandHandler implements IStartFumigationCommandHandler {
    private final String TOPIC = "yanapay/devices/{deviceCode}/fumigation_request";

    private final GreenhouseRepository greenhouseRepository;
    private final MqttPublisher mqttPublisher;

    @Autowired
    public StartFumigationCommandHandler(GreenhouseRepository greenhouseRepository, MqttPublisher mqttPublisher) {
        this.greenhouseRepository = greenhouseRepository;
        this.mqttPublisher = mqttPublisher;
    }

    @Override
    public String handle(StartFumigationCommand command) {
        var greenhouse = greenhouseRepository.findById(command.greenhouseId())
                .orElseThrow(() -> new ResourceNotFoundException("Greenhouse not found with id: " + command.greenhouseId()));

        var devices = greenhouse.getDevices();

        if (devices.isEmpty()) {
            throw new ResourceNotFoundException("No devices found for greenhouse with id: " + command.greenhouseId());
        }

        for (var device : devices) {
            String topic = TOPIC.replace("{deviceCode}", device.getDeviceCode());

            var fumigationNotification = IrrigationFactory.createStartIrrigationMessageSent(device.getDeviceCode());

            String payload = new Gson().toJson(fumigationNotification);

            mqttPublisher.publish(topic, payload);
        }

        return "Fumigation request sent to greenhouse: " + greenhouse.getId();
    }
}
