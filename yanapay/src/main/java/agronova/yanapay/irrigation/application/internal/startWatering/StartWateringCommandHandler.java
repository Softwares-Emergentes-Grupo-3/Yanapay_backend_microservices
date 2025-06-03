package agronova.yanapay.irrigation.application.internal.startWatering;

import agronova.yanapay.greenhouses.domain.model.infrastructure.persistence.jpa.repositories.GreenhouseRepository;
import agronova.yanapay.irrigation.domain.models.factories.IrrigationFactory;
import agronova.yanapay.irrigation.domain.services.startWatering.IStartWateringCommandHandler;
import agronova.yanapay.irrigation.domain.services.startWatering.StartWateringCommand;
import agronova.yanapay.monitoring.domain.model.aggregates.Device;
import agronova.yanapay.shared.application.MqttPublisher;
import agronova.yanapay.shared.interfaces.exceptions.ResourceNotFoundException;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class StartWateringCommandHandler implements IStartWateringCommandHandler {
    private final String TOPIC = "yanapay/devices/{deviceCode}/watering_request";

    private final GreenhouseRepository greenhouseRepository;
    private final MqttPublisher mqttPublisher;

    @Autowired
    public StartWateringCommandHandler(GreenhouseRepository greenhouseRepository, MqttPublisher mqttPublisher) {
        this.greenhouseRepository = greenhouseRepository;
        this.mqttPublisher = mqttPublisher;
    }

    @Override
    public String handle(StartWateringCommand command) {
        var greenhouse = greenhouseRepository.findById(command.greenhouseId())
                .orElseThrow(() -> new ResourceNotFoundException("Greenhouse not found with id: " + command.greenhouseId()));

        Set<Device> devices = greenhouse.getDevices();

        if (devices.isEmpty()) {
            throw new ResourceNotFoundException("No devices found for greenhouse with id: " + command.greenhouseId());
        }

        for (Device device : devices) {
            String topic = TOPIC.replace("{deviceCode}", device.getDeviceCode());

            var wateringNotification = IrrigationFactory.createStartIrrigationMessageSent(device.getDeviceCode());

            String payload = new Gson().toJson(wateringNotification);

            mqttPublisher.publish(topic, payload);
        }

        return "Watering request sent to greenhouse: " + greenhouse.getId();
    }
}
