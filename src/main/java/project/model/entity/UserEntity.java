package project.model.entity;

import com.google.gson.Gson;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.io.Serializable;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class UserEntity implements Serializable {

    @Setter
    @Getter
    private String email;
    @Setter
    @Getter
    private String password;

    @Getter
    @ManyToMany(
            fetch = FetchType.EAGER, cascade = CascadeType.PERSIST
    )
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<UserRoleEntity> roles = new ArrayList<>();

    public UserEntity(String firstName, String lastName, Integer apartmentNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NonNull
    private String firstName;


    @Column
    private String lastName;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity people = (UserEntity) o;
        return firstName.equals(people.firstName) && lastName.equals(people.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

//    public String toStringRest() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(firstName).append(" ").append(lastName).append(System.lineSeparator())
//                .append("-------").append("ApartmentNumber ").append(System.lineSeparator())
//                .append("--------------").append("Phone number: ").append(System.lineSeparator());
//        return sb.toString();
//    }

    public UserEntity setRoles(List<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }
}
