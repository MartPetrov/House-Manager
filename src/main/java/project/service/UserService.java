package project.service;

import project.model.dto.*;
import project.model.entity.UserEntity;

public interface UserService {

    void registerUser(UserRegistrationDTO userRegistration);

    void addModerator(UserModeratorDTO userModerator, BuildingDTO buildingDTO);

    UserEntity findUserByEmail(String email);

    void addUserInBuilding(UserInBuildingDTO userDTO, BuildingDTO buildingDTO);

    void removeUser(Long id, Long building_id);

    void addAdmin(UserAdminDTO userAdminDTO);
}

