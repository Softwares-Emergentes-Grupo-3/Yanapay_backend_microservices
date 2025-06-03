package agronova.yanapay.monitoring.domain.services.updateDeviceConnectionStatus;

public record UpdateDeviceConnectionStatusCommand(String deviceCode, boolean isConnected) {
}
