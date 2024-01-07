package project.service;

import project.entity.People;

import java.util.List;

public interface PeopleService {

    String importPeople(String firstName,String lastName,String phoneNumber,Integer apartmentNumber);

    List<People> findAllPeople();

    String findAllPeopleRest();

    String deleteAllPeople(String password);

}

