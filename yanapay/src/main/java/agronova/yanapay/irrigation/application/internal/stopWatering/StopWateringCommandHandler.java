package agronova.yanapay.irrigation.application.internal.stopWatering;

import agronova.yanapay.greenhouses.infrastructure.infrastructure.persistence.jpa.repositories.GreenhouseRepository;
import agronova.yanapay.irrigation.domain.models.factories.IrrigationFactory;
import agronova.yanapay.irrigation.domain.services.stopWatering.IStopWateringCommandHandler;
import agronova.yanapay.irrigation.domain.services.stopWatering.StopWateringCommand;
import agronova.yanapay.shared.application.MqttPublisher;
import agronova.yanapay.shared.interfaces.exceptions.ResourceNotFoundException;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class StopWateringCommandHandler implements IStopWateringCommandHandler {
    private final String TOPIC = "yanapay/devices/{deviceCode}/watering_request";

    private final GreenhouseRepository greenhouseRepository;
    private final MqttPublisher mqttPublisher;

    public StopWateringCommandHandler(GreenhouseRepository greenhouseRepository, MqttPublisher mqttPublisher) {
        this.greenhouseRepository = greenhouseRepository;
        this.mqttPublisher = mqttPublisher;
    }

    @Override
    public String handle(StopWateringCommand command) {
        var greenhouse = greenhouseRepository.findById(command.greenhouseId())
                .orElseThrow(() -> new ResourceNotFoundException("Greenhouse not found with id: " + command.greenhouseId()));

        var devices = greenhouse.getDevices();

        if (devices.isEmpty()) {
            throw new ResourceNotFoundException("No devices found for greenhouse with id: " + command.greenhouseId());
        }

        for (var device : devices) {
            String topic = TOPIC.replace("{deviceCode}", device.getDeviceCode());

            var stopWateringNotification = IrrigationFactory.createStopIrrigationMessageSent(device.getDeviceCode());

            String payload = new Gson().toJson(stopWateringNotification);

            mqttPublisher.publish(topic, payload);
        }

        return "Watering stop request sent to greenhouse: " + greenhouse.getId();
    }
}
