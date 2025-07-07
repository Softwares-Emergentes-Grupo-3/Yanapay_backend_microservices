package agronova.yanapay.irrigation.domain.services.stopFumigation;

public interface IStopFumigationCommandHandler {
    String handle(StopFumigationCommand command);
}
