package project.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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

    @OneToMany
    private List<BillEntity> bills;

    @ManyToMany
    private List<UserEntity> users;

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
    }
}
