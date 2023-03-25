package project.service.impl;

import org.springframework.stereotype.Service;
import project.entity.People;
import project.repositories.PeopleRepository;
import project.service.PeopleService;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static project.constans.Paths.TXT_PEOPLE;

@Service
public class PeopleServiceImpl implements PeopleService {


    private final PeopleRepository peopleRepository;


    public PeopleServiceImpl(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }




    @Override
    @PostConstruct
    public void importPeople() throws IOException {
        if (this.peopleRepository.findAll().isEmpty()) {
        List<String> strings = Files.readAllLines(TXT_PEOPLE);
        for (String currentLine: strings) {
            String[] split = currentLine.split(",");
            People newPeople = new People(split[0],split[1],split[3]);
            if (!split[2].isEmpty()) {
                newPeople.setPhoneNumber(split[2]);
            }
            this.peopleRepository.save(newPeople);
            System.out.println("Successful imported " + newPeople.getFirst_name() + " " + newPeople.getSecond_name());
        }

        }
    }





}
