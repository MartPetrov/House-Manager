package project.model.entity;

import jakarta.persistence.*;
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

    @OneToMany
    private List<UserEntity> users;

    @Column
    @NonNull
    private String address;

    public BuildingEntity(@NonNull String number, @NonNull String address) {
        this.address = address;
        this.bills = new ArrayList<>();
    }



}
