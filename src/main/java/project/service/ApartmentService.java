package project.service;

import project.entity.UserEntity;

import java.util.List;

public interface ApartmentService {

    String importApartment(String number, String floor, List<UserEntity> peopleList);
}
