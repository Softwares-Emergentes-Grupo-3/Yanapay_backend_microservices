package agronova.yanapay.shared.infrastructure.seed;

import agronova.yanapay.greenhouses.domain.model.aggregates.Greenhouse;
import agronova.yanapay.greenhouses.domain.model.infrastructure.persistence.jpa.repositories.GreenhouseRepository;
import agronova.yanapay.monitoring.domain.model.aggregates.Device;
import agronova.yanapay.monitoring.infrastructure.persistence.jpa.repositories.DeviceRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomDataInitializer implements ApplicationRunner {
    private final DeviceRepository deviceRepository;
    private final GreenhouseRepository greenhouseRepository;

    public CustomDataInitializer(DeviceRepository deviceRepository, GreenhouseRepository greenhouseRepository) {
        this.deviceRepository = deviceRepository;
        this.greenhouseRepository = greenhouseRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Device> devices = deviceRepository.findAll();

        if (!devices.isEmpty()) {
            return;
        }

        seedDevices();
        seedGreenhouses();
    }

    private void seedDevices() {
        Device device1 = new Device();
        device1.setDeviceCode("xX_Device123_Xx");
        device1.setOnline(false);

        Device device2 = new Device();
        device2.setDeviceCode("testing_device");
        device2.setOnline(false);

        deviceRepository.save(device1);
        deviceRepository.save(device2);
    }

    private void seedGreenhouses() {
        Greenhouse greenhouse1 = new Greenhouse();
        greenhouse1.setName("GreenhouseABC");

        greenhouseRepository.save(greenhouse1);
    }
}
