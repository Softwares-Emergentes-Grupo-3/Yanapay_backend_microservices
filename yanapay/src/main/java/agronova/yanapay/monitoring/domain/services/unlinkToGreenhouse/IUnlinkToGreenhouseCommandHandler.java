package agronova.yanapay.monitoring.domain.services.unlinkToGreenhouse;

public interface IUnlinkToGreenhouseCommandHandler {
    /**
     * Handles the unlinking of a device from a greenhouse.
     *
     * @param command The command containing the device code and greenhouse ID to unlink.
     * @return A message indicating the result of the unlinking operation.
     */
    String handle(UnlinkToGreenhouseCommand command);
}
