package project.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDto {


    @Size(min = 5, max = 20)
    @NotEmpty
    private String firstName;


    @Size(min = 5, max = 20)
    @NotEmpty
    private String lastName;


    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String phoneNumber;
}
