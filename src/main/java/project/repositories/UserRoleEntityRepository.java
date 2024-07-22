package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.model.entity.UserRoleEntity;
import project.model.enums.UserRoleEnum;

public interface UserRoleEntityRepository extends JpaRepository<UserRoleEntity,Long> {


    UserRoleEntity findByRole(UserRoleEnum roleEnum);
}
