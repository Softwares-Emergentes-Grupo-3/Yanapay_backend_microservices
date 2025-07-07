package agronova.yanapay.monitoring.domain.model.entities;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "device_assignments")
public class DeviceAssignment {
    @Id
    private String id;
    private String deviceCode;
    private Long greenhouseId;

    public DeviceAssignment(String deviceCode, Long greenhouseId) {
        this.deviceCode = deviceCode;
        this.greenhouseId = greenhouseId;
    }
}
