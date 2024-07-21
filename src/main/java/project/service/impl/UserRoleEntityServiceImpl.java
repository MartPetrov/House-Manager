package project.service.impl;

import org.springframework.stereotype.Service;
import project.model.entity.UserRoleEntity;
import project.repositories.UserRoleEntityRepository;
import project.service.UserRoleEntityService;

@Service
public class UserRoleEntityServiceImpl implements UserRoleEntityService {
    private final UserRoleEntityRepository userRoleEntityRepository;

    public UserRoleEntityServiceImpl (UserRoleEntityRepository userRepository) {
        this.userRoleEntityRepository = userRepository;
    }

    @Override
    public UserRoleEntity findUserRolesById(int id) {
        return this.userRoleEntityRepository.findById(id);
    }
}
