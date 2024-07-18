package project.entity;


import jakarta.persistence.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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


    @ManyToMany
    private List<Bill> bills;

    @OneToMany
    private List<UserEntity> peoples;

    public Apartment(@NonNull String number, @NonNull String floor) {
        this.number = number;
        this.floor = floor;
        this.bills = new ArrayList<>();
    }
}
