package project.entity;

import com.google.gson.Gson;
import jakarta.persistence.*;
import lombok.*;
import project.enums.TypeOfBill;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Getter
@Setter
@Table(name = "Bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Min(1)
    @Max(12)
    private int month;

    @Column
    @NonNull
    private String firstNumber;

    @Column
    private String secondNumber;

    @Column
    @NonNull
    private String date;

    @Column
    @NonNull
    private Double sum;

    @Column()
    @NonNull
    @Enumerated(EnumType.STRING)
    private TypeOfBill typeOfBill;

    public Bill() {}

    public Bill(String firstNumber, String secondNumber, String date, Double sum, int month, TypeOfBill typeOfBill) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.date = date;
        this.sum = sum;
        this.month = month;
        this.typeOfBill = typeOfBill;
    }

    public String toStringRest() {
        return "Bill For month: " + getMonth() + System.lineSeparator() +
                "-With number: " + getFirstNumber() + " / " + "from date: " + getDate() + System.lineSeparator() +
                "--And " + getSecondNumber() + " / " + "from date: " + getDate() + System.lineSeparator() +
                "---For sum: " + String.format("%.2f", getSum()) + System.lineSeparator();
    }

 @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
