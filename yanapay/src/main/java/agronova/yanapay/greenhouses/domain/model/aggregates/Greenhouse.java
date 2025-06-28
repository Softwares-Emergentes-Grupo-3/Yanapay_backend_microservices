package agronova.yanapay.greenhouses.domain.model.aggregates;

import agronova.yanapay.iam.domain.models.aggregates.User;
import agronova.yanapay.monitoring.domain.model.aggregates.Device;
import agronova.yanapay.shared.domain.model.aggregates.BaseDomainModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Getter
@Setter
@Table(name = "greenhouses")
public class Greenhouse extends BaseDomainModel {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "planting_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate plantingDate;

    @OneToMany(mappedBy = "greenhouse", cascade = CascadeType.PERSIST, orphanRemoval = false)
    private Set<Device> devices;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //Intento de CREAT
    public Greenhouse() {
        super();
        this.name = "";
        this.plantingDate = LocalDate.now();
    }

    public Greenhouse(String name, LocalDate plantingDate, User user) {
        super();
        this.name = name;
        this.plantingDate = plantingDate;
        this.user = user;
    }

    public Greenhouse updateName(String name) {
        this.name = name;

        return this;
    }
}
