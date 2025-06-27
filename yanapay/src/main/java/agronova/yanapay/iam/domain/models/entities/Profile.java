package agronova.yanapay.iam.domain.models.entities;

import agronova.yanapay.iam.domain.models.aggregates.User;
import agronova.yanapay.shared.domain.model.aggregates.BaseDomainModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "profiles")
public class Profile extends BaseDomainModel {
    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @OneToOne(mappedBy = "profile")
    private User user;

    public Profile() {
        super();
        this.firstName = "";
        this.lastName = "";
    }

    public Profile(String firstName, String lastName) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
