package project.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

public class UserRegistrationDTO {
    @NotEmpty
    @Size(min = 5, max = 20)
    @Getter
    @Setter
    private String firstName;

    @NotEmpty
    @Size(min = 5, max = 20)
    @Getter
    @Setter
    private String lastName;

    @NotEmpty
    @Getter
    @Setter
    private String password;

    @NotEmpty
    @Email
    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String phone_Number;

    @Getter
    @Setter
    private String Apartment;


    @Override
    public String toString() {
        return "UserRegistrationDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + (password == null ? "N/A" : "[PROVIDED]") + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
