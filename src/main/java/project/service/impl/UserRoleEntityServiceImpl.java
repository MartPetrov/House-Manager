package project.service.impl;

import org.springframework.stereotype.Service;
import project.model.entity.UserRoleEntity;
import project.model.enums.UserRoleEnum;
import project.repositories.UserRoleEntityRepository;
import project.service.UserRoleEntityService;

@Service
public class UserRoleEntityServiceImpl implements UserRoleEntityService {
    private final UserRoleEntityRepository userRoleEntityRepository;

    public UserRoleEntityServiceImpl (UserRoleEntityRepository userRepository) {
        this.userRoleEntityRepository = userRepository;
    }

    @Override
    public UserRoleEntity findUserRolesByRole(UserRoleEnum role) {
        return this.userRoleEntityRepository.findByRole(role);
    }
}
