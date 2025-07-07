package agronova.yanapay.monitoring.interfaces.rest.getDeviceByDeviceCodeController;

import agronova.yanapay.monitoring.domain.model.aggregates.Device;

public class GetDeviceByDeviceCodeResponse {
    public final GetDeviceByDeviceCodeDTO data;

    public GetDeviceByDeviceCodeResponse(Device device) {
        this.data = new GetDeviceByDeviceCodeDTO(device.getId(), device.getDeviceCode(), device.isOnline());
    }
}

record GetDeviceByDeviceCodeDTO(
        Long id, String deviceCode, boolean isOnline
) {
}
