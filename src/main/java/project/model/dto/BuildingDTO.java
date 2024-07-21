package project.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class BuildingDTO {
    @NotEmpty
    @Size(min = 10)
    @Getter
    @Setter
    private String address;
}
