package agronova.yanapay.greenhouses.domain.model.aggregates;

import agronova.yanapay.monitoring.domain.model.aggregates.Device;
import agronova.yanapay.shared.domain.model.aggregates.BaseDomainModel;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "greenhouses")
public class Greenhouse extends BaseDomainModel {
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "greenhouse", cascade = CascadeType.PERSIST, orphanRemoval = false)
    private Set<Device> devices;
}
