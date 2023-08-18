package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.entity.People;

import java.util.List;

@Repository
public interface PeopleRepository extends JpaRepository<People,Long> {

    @Override
    List<People> findAll();
    @Query(
            value = "SELECT p FROM People as p " +
                    "WHERE p.first_name = ?1 and p.second_name = ?2")
   People findPeopleByFirst_nameAndSecond_name(String firstName,String secondName);


    @Override
    void deleteAll();
}
