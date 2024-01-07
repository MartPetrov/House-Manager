package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.service.PeopleService;

@Controller
public class PeopleControllerSecond {

    @Autowired
    private final PeopleService peopleService;

    @Autowired
    public PeopleControllerSecond(PeopleService peopleService) {
        this.peopleService = peopleService;
    }


    @GetMapping("/people")
    public String findAllPeople(Model model) {
        model.addAttribute("getAllPeople",peopleService.findAllPeople());
        return "people.html";
    }
}
