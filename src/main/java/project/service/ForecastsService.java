package project.service;

import project.model.dto.WeatherForecastDTO;
import reactor.core.publisher.Mono;

public interface ForecastsService {

    Mono<WeatherForecastDTO> getForecast(String city);
}
