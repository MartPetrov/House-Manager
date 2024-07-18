package project.service;

import project.entity.UserEntity;

import java.util.List;

public interface PeopleService {

    String importPeople(String firstName,String lastName,String phoneNumber,Integer apartmentNumber);

    List<UserEntity> findAllPeople();

    String findAllPeopleRest();

    String deleteAllPeople(String password);

}

