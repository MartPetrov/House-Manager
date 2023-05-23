package project.service.impl;

import org.springframework.stereotype.Service;
import project.entity.People;
import project.repositories.PeopleRepository;
import project.service.PeopleService;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static project.constans.Paths.TXT_PEOPLE;

@Service
public class PeopleServiceImpl implements PeopleService {
    private final PeopleRepository peopleRepository;
    public PeopleServiceImpl(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public String importPeople(String firstName, String lastName, String phoneNumber, String apartmentNumber) {
        People newPeople = new People(firstName, lastName, apartmentNumber);
        People byFirstNameAndSecondName = peopleRepository.findPeopleByFirst_nameAndSecond_name(firstName, lastName);
        if (!phoneNumber.isEmpty()) {
            newPeople.setPhoneNumber(phoneNumber);
        }
        if (byFirstNameAndSecondName == null) {
            this.peopleRepository.save(newPeople);
            return newPeople + "Successful imported";
        }
        return "This user is in DataBase";
    }
    @Override
    public String findAllPeople() {
        List<People> all = this.peopleRepository.findAll();
        StringBuilder sb = new StringBuilder();
        all.forEach(sb::append);
        return sb.toString();
    }
}
