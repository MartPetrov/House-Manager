package project.service;

import project.model.dto.*;
import project.model.entity.UserEntity;
import project.model.user.HouseManagerUserDetails;

import java.util.Optional;

public interface UserService {

    void registerUser(UserRegistrationDTO userRegistration);

    void addModerator(UserModeratorDTO userModerator, BuildingDTO buildingDTO);

    UserEntity findUserByEmail(String email);

    void addUserInBuilding(UserInBuildingDTO userDTO, BuildingDTO buildingDTO);

    void removeUser(Long id, Long building_id);

    void addAdmin(UserAdminDTO userAdminDTO);

    Optional<HouseManagerUserDetails> getCurrentUser();

    void updateUser(UserUpdateDto userDTO);
}

