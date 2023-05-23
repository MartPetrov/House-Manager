package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.service.PeopleService;

@RestController
public class PeopleController {

    private PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @PostMapping(value = "/importPeople",params = {"firstName","secondName","phoneNumber","apartmentNumber"})
    public @ResponseBody String importPeople(@RequestParam(value = "firstName") String firstName,
                                             @RequestParam(value = "secondName") String secondName,
                                             @RequestParam(value = "phoneNumber") String phoneNumber,
                                             @RequestParam(value = "apartmentNumber") String apartmentNumber) {
        return this.peopleService.importPeople(firstName, secondName,phoneNumber,apartmentNumber);
    }
    //         http://localhost:8080/importPeople?firstName=Ivan&secondName=Ivanov&phoneNumber=09248421841&apartmentNumber=6


    @RequestMapping(value = "/findAllPeople")
    public @ResponseBody String findAllPeople() {
       return this.peopleService.findAllPeople();
    }
    //           http://localhost:8080/findAllPeople

}
