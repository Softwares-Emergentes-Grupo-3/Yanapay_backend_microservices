package agronova.yanapay.monitoring.interfaces.rest.getAllDevices;

import agronova.yanapay.monitoring.domain.model.aggregates.Device;

import java.util.List;

public class GetAllDevicesResponse {
    public final List<GetAllDevicesDTO> data;

    public GetAllDevicesResponse(List<Device> devices) {
        this.data = devices.stream()
                .map(device -> new GetAllDevicesDTO(
                        device.getId(),
                        device.getDeviceCode(),
                        device.isOnline()
                ))
                .toList();
    }
}

record GetAllDevicesDTO(Long id, String deviceCode, boolean isOnline) {
}
