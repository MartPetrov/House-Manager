package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.entity.Apartment;
import project.entity.People;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment,Long> {

    @Override
    List<Apartment> findAll();


    Optional<Apartment> findApartmentByNumber(String number);
}
