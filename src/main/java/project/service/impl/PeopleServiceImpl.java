package project.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.entity.People;
import project.repositories.PeopleRepository;
import project.service.PeopleService;

import java.util.Comparator;
import java.util.List;

import static project.constans.Messages.*;

@Service
@AllArgsConstructor
public class PeopleServiceImpl implements PeopleService {
    private final PeopleRepository peopleRepository;

    @Override
    public String importPeople(String firstName, String lastName, String phoneNumber, Integer apartmentNumber) {
        People newPeople = new People(firstName, lastName, apartmentNumber);
        People byFirstNameAndSecondName = peopleRepository.findPeopleByFirst_nameAndSecond_name(firstName, lastName);
        if (phoneNumber != null && phoneNumber.startsWith("359")) {
            newPeople.setPhoneNumber(phoneNumber);
        } else {
            throw new IllegalArgumentException("Wrong Phone Number");
        }
        if (byFirstNameAndSecondName == null) {
            this.peopleRepository.save(newPeople);
            return SUCCESSFUL_IMPORT_PEOPLE + newPeople;
        }
        return USER_IS_IN_DATABASE;
    }

    @Override
    public String findAllPeople() {
        List<People> allPeopleCompareApartment =
                this.peopleRepository.findAll().stream()
                        .sorted(Comparator.comparingInt(People::getApartmentNumber))
                        .toList();
        StringBuilder sb = new StringBuilder();
        allPeopleCompareApartment.forEach(sb::append);
        return sb.toString();
    }

    @Override
    public String deleteAllPeople(String password) {
        if (password.equals(MSP)) {
            this.peopleRepository.deleteAll();
            return DELETE_ALL_PEOPLE_FROM_DB;
        }
        return WRONG_PASSWORD;
    }
}
