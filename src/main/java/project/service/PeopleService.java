package project.service;

import java.io.IOException;

public interface PeopleService {


    String importPeople(String firstName,String lastName,String phoneNumber,String apartmentNumber);


    String findAllPeople();

}

