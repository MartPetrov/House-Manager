package project.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserInBuildingDTO {

    @NotEmpty
    @Email
    private String email;


    @Email
    private String apartmentNumber;


}
