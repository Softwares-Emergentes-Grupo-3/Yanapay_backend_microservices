package agronova.yanapay.irrigation.domain.models.factories;

import agronova.yanapay.irrigation.domain.models.entities.WateringAction;
import agronova.yanapay.irrigation.domain.models.events.IrrigationMessageSent;

public class IrrigationFactory {
    public static IrrigationMessageSent createStartIrrigationMessageSent(String deviceCode) {
        return new IrrigationMessageSent(deviceCode, WateringAction.START.name().toLowerCase());
    }

    public static IrrigationMessageSent createStopIrrigationMessageSent(String deviceCode) {
        return new IrrigationMessageSent(deviceCode, WateringAction.STOP.name().toLowerCase());
    }
}
