
package project.web.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.model.dto.WeatherForecastDTO;
import project.service.ForecastsService;

@Controller
public class WeatherController {


    private final ForecastsService forecastService;

    @Autowired
    public WeatherController(ForecastsService forecastService) {
        this.forecastService = forecastService;
    }

    @GetMapping("/get-forecast")
    public String getForecast(@RequestParam String city, Model model) {
        WeatherForecastDTO forecast = forecastService.getForecast(city);
        model.addAttribute("currentForecast", forecast);
        return "current-weather";
    }
    //    http://localhost:8081/findForecastsByCity?city=Plovdiv
}

