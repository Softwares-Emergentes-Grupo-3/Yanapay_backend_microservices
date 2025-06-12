package agronova.yanapay.greenhouses.interfaces.getGreenhouseById;

import agronova.yanapay.monitoring.domain.model.aggregates.Device;

public record DeviceDTO(Long id, String deviceCode, boolean isOnline) {
    public static DeviceDTO fromEntity(Device device) {
        return new DeviceDTO(device.getId(), device.getDeviceCode(), device.isOnline());
    }
}
