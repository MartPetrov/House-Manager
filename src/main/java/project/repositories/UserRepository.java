package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    @Override
    List<UserEntity> findAll();
    @Query(
            value = "SELECT usr FROM UserEntity as usr " +
                    "WHERE usr.first_name = ?1 and usr.second_name = ?2")
    UserEntity findPeopleByFirst_nameAndSecond_name(String firstName, String secondName);


    @Override
    void deleteAll();

    Optional<UserEntity> findByEmail(String email);
}
