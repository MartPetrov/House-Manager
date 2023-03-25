package project.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "peoples")
public class People {

    public People(String first_name,String second_name, String apartmentNumber) {
        this.first_name = first_name;
        this.second_name = second_name;
        this.apartmentNumber = apartmentNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String first_name;


    @Column
    private String second_name;

    @Column
    private String phoneNumber;

    @Column(nullable = false)
    private String apartmentNumber;

    @Column
    private double importMoney;


    @Column
    private double expensesMoney;


    @Column
    private double allMoneyInAccount;


}
