package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.model.entity.UserRoleEntity;

public interface UserRoleEntityRepository extends JpaRepository<UserRoleEntity,Long> {


    UserRoleEntity findById(int id);
}
