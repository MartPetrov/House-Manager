package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.entity.People;
import project.service.PeopleService;

import java.util.List;

@Controller
public class PeopleControllerSecond {

    @Autowired
    private final PeopleService peopleService;

    @Autowired
    public PeopleControllerSecond(PeopleService peopleService) {
        this.peopleService = peopleService;
    }


    @GetMapping("/people")
    public List<People> findAllPeople() {
        return peopleService.findAllPeople();
    }
}
