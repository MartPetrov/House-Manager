
package project.web.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.model.dto.WeatherForecastDTO;
import project.service.ForecastsService;
import reactor.core.publisher.Mono;

@RestController
public class WeatherController {


    private final ForecastsService forecastService;

    @Autowired
    public WeatherController(ForecastsService forecastService) {
        this.forecastService = forecastService;
    }

    @GetMapping("/get-forecast")
    public String getForecast(@RequestParam String city, Model model) {
        Mono<WeatherForecastDTO> forecast = forecastService.getForecast(city);
        model.addAttribute("currentForecast", forecast);
        return "current-weather";
    }
    //    http://localhost:8081/findForecastsByCity?city=Plovdiv
}

