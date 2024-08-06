package project.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import project.model.dto.WeatherForecastDTO;
import project.service.ForecastsService;
import reactor.core.publisher.Mono;

@Service
public class ForecastsServiceImpl implements ForecastsService {

    private final WebClient webClient;

    public ForecastsServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081").build();
    }

    public Mono<WeatherForecastDTO> getForecast(String city) {

        Mono<WeatherForecastDTO> forecastDTOMono = this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/forecast")
                        .queryParam("city", city)
                        .build())
                .retrieve()
                .bodyToMono(WeatherForecastDTO.class);


        return forecastDTOMono;
    }
}
