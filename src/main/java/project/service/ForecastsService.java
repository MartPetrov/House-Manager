package project.service;

import project.model.dto.WeatherForecastDTO;

public interface ForecastsService {

    WeatherForecastDTO getForecast(String city);
}
