package project.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class BuildingDTO {
    @NotEmpty
    @Getter
    @Setter
    private String city;

    @NotEmpty
    @Getter
    @Setter
    private String street;

    @NotEmpty
    @Getter
    @Setter
    private String number;

}
