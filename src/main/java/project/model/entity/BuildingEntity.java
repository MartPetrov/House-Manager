package project.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "Buildings")
@NoArgsConstructor
public class BuildingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER)
    private List<BillEntity> bills;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserEntity> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @Getter
    @Setter
    private List<UserEntity> moderators;

    @NotEmpty
    @Getter
    @Setter
    private String city;

    @NotEmpty
    @Getter
    @Setter
    private String street;

    @NotEmpty
    @Getter
    @Setter
    private String number;

    public BuildingEntity(String city, String street, String number) {
        this.city = city;
        this.street = street;
        this.number = number;
        this.bills = new ArrayList<>();
        this.users = new ArrayList<>();
        this.moderators = new ArrayList<>();
    }
}
