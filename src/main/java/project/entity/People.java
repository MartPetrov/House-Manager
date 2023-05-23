package project.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "peoples")
public class People {

    public People(String first_name, String second_name, String apartmentNumber) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        People people = (People) o;
        return first_name.equals(people.first_name) && second_name.equals(people.second_name) && Objects.equals(phoneNumber, people.phoneNumber) && apartmentNumber.equals(people.apartmentNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first_name, second_name, phoneNumber, apartmentNumber);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(first_name).append(" ").append(second_name).append(System.lineSeparator())
                .append("-------").append("ApartmentNumber ").append(apartmentNumber).append(System.lineSeparator())
                .append("--------------").append("Phone number: ").append(phoneNumber).append(System.lineSeparator());
        return sb.toString();
    }
}
