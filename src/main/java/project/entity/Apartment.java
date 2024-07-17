package project.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "Apartments")
@NoArgsConstructor
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    @NonNull
    private String number;

    @Column
    @NonNull
    private String floor;


    @OneToMany
    private List<Bill> bills;

    @OneToMany
    private List<People> peoples;

    public Apartment(Long id, @NonNull String number, @NonNull String floor) {
        this.id = id;
        this.number = number;
        this.floor = floor;
        this.bills = new ArrayList<>();
    }
}
