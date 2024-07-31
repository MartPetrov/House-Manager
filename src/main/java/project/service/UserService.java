package project.service;

import project.model.dto.BuildingDTO;
import project.model.dto.UserModeratorDTO;
import project.model.dto.UserRegistrationDTO;
import project.model.entity.UserEntity;

public interface UserService {

//    String importPeople(String firstName,String lastName,String phoneNumber,Integer apartmentNumber);
//
//    List<UserEntity> findAllPeople();
//
//    String findAllPeopleRest();
//
//    String deleteAllPeople(String password);

    void registerUser(UserRegistrationDTO userRegistration);

    void addModerator(UserModeratorDTO userModerator, BuildingDTO buildingDTO);

    UserEntity findUserByEmail(String email);

}

