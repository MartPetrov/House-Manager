package project.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class UserModeratorDTO {
    @NotEmpty
    @Email
    @Getter
    @Setter
    private String email;
}
