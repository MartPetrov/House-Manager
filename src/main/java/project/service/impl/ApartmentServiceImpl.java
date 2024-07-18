package project.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.entity.Apartment;
import project.entity.UserEntity;
import project.repositories.ApartmentRepository;
import project.service.ApartmentService;

import java.util.List;

import static project.constans.Messages.SUCCESSFUL_IMPORT_APARTMENT;

@Service
@AllArgsConstructor
public class ApartmentServiceImpl implements ApartmentService {
    private final ApartmentRepository apartmentRepository;


    @Override
    public String importApartment(String number, String floor, List<UserEntity> peopleList) {
        Apartment apartment = new Apartment(number, floor);
        apartment.setPeoples(peopleList);
        this.apartmentRepository.save(apartment);
        return SUCCESSFUL_IMPORT_APARTMENT + number;
    }
}
