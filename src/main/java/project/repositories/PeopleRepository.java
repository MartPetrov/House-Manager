package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.entity.People;

@Repository
public interface PeopleRepository extends JpaRepository<People,Long> {


}
