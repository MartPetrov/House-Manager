package project.service;

public interface PeopleService {

    String importPeople(String firstName,String lastName,Integer phoneNumber,Integer apartmentNumber);

    String findAllPeople();

    String deleteAllPeople(String password);

}

