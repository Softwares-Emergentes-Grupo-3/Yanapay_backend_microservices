package agronova.yanapay.monitoring.interfaces.rest.getDeviceByDeviceCodeController;

public record GetDeviceByDeviceCodeResponse(
        Long id, String deviceCode, boolean isOnline
) {
}
