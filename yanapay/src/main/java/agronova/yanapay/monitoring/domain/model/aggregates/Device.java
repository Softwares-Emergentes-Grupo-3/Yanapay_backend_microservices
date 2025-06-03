package agronova.yanapay.monitoring.domain.model.aggregates;

import agronova.yanapay.greenhouses.domain.model.aggregates.Greenhouse;
import agronova.yanapay.shared.domain.model.aggregates.BaseDomainModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "devices")
public class Device extends BaseDomainModel {
    @Column(name = "device_code", unique = true, nullable = false)
    private String deviceCode;

    @Column(name = "is_online", nullable = false)
    private boolean isOnline;

    @ManyToOne()
    @JoinColumn(name = "greenhouse_id", nullable = true)
    private Greenhouse greenhouse;

    public Device() {
        super();
        this.deviceCode = UUID.randomUUID().toString();
        this.greenhouse = null;
        this.isOnline = false;
    }

    public Device(String deviceCode) {
        super();
        this.deviceCode = deviceCode;
        this.greenhouse = null;
        this.isOnline = false;
    }

    public Device updateConnectionStatus(boolean isOnline) {
        this.isOnline = isOnline;

        return this;
    }

    public void linkToGreenhouse(Greenhouse greenhouse) {
        this.greenhouse = greenhouse;
    }

    public void unlinkFromGreenhouse() {
        this.greenhouse = null;
    }
}
