package agronova.yanapay.iam.domain.models.aggregates;

import agronova.yanapay.greenhouses.domain.model.aggregates.Greenhouse;
import agronova.yanapay.iam.domain.models.entities.Profile;
import agronova.yanapay.shared.domain.model.aggregates.BaseDomainModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseDomainModel {

    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private Profile profile;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, orphanRemoval = false)
    private Set<Greenhouse> greenhouses;

    public User() {
        super();
        this.email = "";
        this.password = "";
        this.profile = new Profile();
    }

    public User(String email, String password, Profile profile) {
        super();
        this.email = email;
        this.password = password;
        this.profile = profile;
    }
}
