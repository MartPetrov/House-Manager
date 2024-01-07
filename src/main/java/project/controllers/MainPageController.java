package project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    @GetMapping()
    public String homePage(Model model) {
        return "/MainPage/mainPage.html";
    }
}
