package project.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import project.model.enums.TypeOfBill;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Getter
@Setter
@Table(name = "Bills")
public class BillEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Min(1)
    @Max(12)
    private int month;

    @Column
    @NonNull
    private String noteNumber;


    @Column
    @NonNull
    private String date;

    @Column
    @NonNull
    private Double sum;

    @Column()
    @NonNull
    @Enumerated(EnumType.STRING)
    private TypeOfBill type;

    public BillEntity() {
    }
}


