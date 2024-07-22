package project.service;

import project.model.entity.UserRoleEntity;
import project.model.enums.UserRoleEnum;

public interface UserRoleEntityService {

    UserRoleEntity findUserRolesByRole(UserRoleEnum roleEnum);
}
