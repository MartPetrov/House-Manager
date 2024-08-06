package project.model.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class WeatherForecastDTO {

    @Id
    private Long id;

    @NotEmpty
    private String city;

    @NotEmpty
    private String minTemperature;

    @NotEmpty
    private String maxTemperature;

}
