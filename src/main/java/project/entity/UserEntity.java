package project.entity;

import com.google.gson.Gson;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import project.enums.UserRoleEnum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "peoples")
public class UserEntity implements Serializable {

    private String email;
    private String password;

    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<UserRoleEntity> roles = new ArrayList<>();

    public UserEntity(String first_name, String second_name, Integer apartmentNumber) {
        this.first_name = first_name;
        this.second_name = second_name;
        this.apartmentNumber = apartmentNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NonNull
    private String first_name;


    @Column
    private String second_name;

    @Column
    private String phoneNumber;

    @Column
    @NonNull
    private Integer apartmentNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity people = (UserEntity) o;
        return first_name.equals(people.first_name) && second_name.equals(people.second_name) && Objects.equals(phoneNumber, people.phoneNumber) && apartmentNumber.equals(people.apartmentNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first_name, second_name, phoneNumber, apartmentNumber);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public String toStringRest() {
        StringBuilder sb = new StringBuilder();
        sb.append(first_name).append(" ").append(second_name).append(System.lineSeparator())
                .append("-------").append("ApartmentNumber ").append(apartmentNumber).append(System.lineSeparator())
                .append("--------------").append("Phone number: ").append(phoneNumber).append(System.lineSeparator());
        return sb.toString();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(List<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }
}
