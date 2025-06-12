package agronova.yanapay.greenhouses.domain.model.aggregates;

import agronova.yanapay.monitoring.domain.model.aggregates.Device;
import agronova.yanapay.shared.domain.model.aggregates.BaseDomainModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Getter
@Setter
@Table(name = "greenhouses")
public class Greenhouse extends BaseDomainModel {
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "greenhouse", cascade = CascadeType.PERSIST, orphanRemoval = false)
    private Set<Device> devices;

    //Intento de CREAT
    public Greenhouse() {
        super();
        this.name = "";
    }

    public Greenhouse(String name) {
        super();
        this.name = name;
    }

    public Greenhouse updateName(String name) {
        this.name = name;

        return this;
    }
}
