package project.service;

import lombok.NonNull;
import project.entity.People;

import java.util.List;

public interface ApartmentService {

    String importApartment(String number, String floor, List<People> peopleList);
}
