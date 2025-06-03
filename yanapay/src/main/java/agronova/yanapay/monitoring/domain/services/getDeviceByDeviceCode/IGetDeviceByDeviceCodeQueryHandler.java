package agronova.yanapay.monitoring.domain.services.getDeviceByDeviceCode;

import agronova.yanapay.monitoring.domain.model.aggregates.Device;

public interface IGetDeviceByDeviceCodeQueryHandler {
    Device handle(GetDeviceByDeviceCodeQuery query);
}
