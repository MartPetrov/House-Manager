package project.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import project.model.dto.WeatherForecastDTO;
import project.service.ForecastsService;
import project.service.exception.ObjectNotFoundException;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class ForecastsServiceImpl implements ForecastsService {

    private final WebClient webClient;
    private final Logger logger = LoggerFactory.getLogger(ForecastsServiceImpl.class);

    public ForecastsServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081").build();
    }

    public WeatherForecastDTO getForecast(String city) {
        logger.info("User send request to WeatherApi");
        WeatherForecastDTO forecastDTOMono = this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/forecast")
                        .queryParam("city", city)
                        .build()).accept(MediaType.APPLICATION_JSON)
                .retrieve().onStatus(s -> s.isSameCodeAs(HttpStatus.NOT_FOUND), clientResponse ->
                        Mono.error(new ObjectNotFoundException("Forecast for this city:  " + city, city)))
                .bodyToMono(WeatherForecastDTO.class).block(Duration.ofSeconds(5));

        return forecastDTOMono;
    }
}
