package project.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import project.model.entity.UserRoleEntity;
import project.model.enums.UserRoleEnum;
import project.repositories.UserRoleEntityRepository;

import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    private final UserRoleEntityRepository userRolesRepository;

    @Autowired
    public DataLoader(UserRoleEntityRepository userRolesRepository) {
        this.userRolesRepository = userRolesRepository;
    }

    public void run(ApplicationArguments args) {
        List<UserRoleEntity> userRoles = userRolesRepository.findAll();
        if (userRoles.isEmpty()) {
            userRolesRepository.save(new UserRoleEntity(UserRoleEnum.USER));
            userRolesRepository.save(new UserRoleEntity(UserRoleEnum.MODERATOR));
            userRolesRepository.save(new UserRoleEntity(UserRoleEnum.ADMIN));
        }
    }
}
