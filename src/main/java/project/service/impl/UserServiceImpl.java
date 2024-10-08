package project.service.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.constant.Constant;
import project.model.dto.*;
import project.model.entity.BuildingEntity;
import project.model.entity.UserEntity;
import project.model.entity.UserRoleEntity;
import project.model.enums.UserRoleEnum;
import project.model.user.HouseManagerUserDetails;
import project.repositories.BuildingRepository;
import project.repositories.UserRepository;
import project.service.UserRoleEntityService;
import project.service.UserService;
import project.service.exception.BuildingNotFoundException;
import project.service.exception.UserAlreadyDoThat;
import project.service.exception.UserNotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final BuildingRepository buildingRepository;
    private final UserRoleEntityService userRoleEntityService;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(ModelMapper modelMapper,
                           PasswordEncoder passwordEncoder,
                           UserRepository userRepository, BuildingRepository buildingRepository, UserRoleEntityService userRoleEntityService) {
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.buildingRepository = buildingRepository;
        this.userRoleEntityService = userRoleEntityService;
    }

    @Override
    public void registerUser(UserRegistrationDTO userRegistration) {
        UserEntity newUser = map(userRegistration);
        newUser.getRoles().add(this.userRoleEntityService.findUserRolesByRole(UserRoleEnum.USER));
        userRepository.save(newUser);
        logger.info("User registered successfully");
    }

    @Override
    public void addModerator(UserModeratorDTO userModeratorDTO, BuildingDTO buildingDTO) {
        Optional<UserEntity> userFromRepo = userRepository.findByEmail(userModeratorDTO.getEmail());
        Optional<BuildingEntity> buildingEntity = buildingRepository.findBuildingEntitiesByAddress(buildingDTO.getCity(), buildingDTO.getStreet(), buildingDTO.getNumber());
        if (buildingEntity.isEmpty()) {
            throw new BuildingNotFoundException("This building is not registered");
        } else if (userFromRepo.isEmpty()) {
            throw new UserNotFoundException("User with email:" + userModeratorDTO.getEmail() + " does not exist");
        } else if (buildingEntity.get().getModerators().contains(userFromRepo.get())) {
            throw new UserAlreadyDoThat("User with email: " + userFromRepo.get().getEmail() + " is already moderator of this building");
        }


        UserEntity userEntity = userFromRepo.get();
        userEntity.getRoles().add(this.userRoleEntityService.findUserRolesByRole(UserRoleEnum.MODERATOR));
        userRepository.save(userEntity);
        BuildingEntity buildingEntityFromRepo = buildingEntity.get();
        List<UserEntity> moderators = buildingEntityFromRepo.getModerators();
        moderators.add(userEntity);
        buildingEntityFromRepo.setModerators(moderators);
        List<UserEntity> users = buildingEntityFromRepo.getUsers();
        users.add(userEntity);
        buildingRepository.save(buildingEntityFromRepo);
        logger.info("User is moderator successfully");

    }

    @Override
    public UserEntity findUserByEmail(String email) {
        Optional<UserEntity> byEmail = this.userRepository.findByEmail(email);
        if (byEmail.isEmpty()) {
            throw new UserNotFoundException("User with email:" + email + " does not exist");
        }
        return byEmail.get();
    }

    @Override
    public void addUserInBuilding(UserInBuildingDTO userDTO, BuildingDTO buildingDTO) {
        Optional<UserEntity> userFromRepo = userRepository.findByEmail(userDTO.getEmail());
        Optional<BuildingEntity> buildingEntity = buildingRepository.findBuildingEntitiesByAddress(buildingDTO.getCity(), buildingDTO.getStreet(), buildingDTO.getNumber());
        if (buildingEntity.isEmpty()) {
            throw new BuildingNotFoundException("This building is not registered");
        } else if (userFromRepo.isEmpty()) {
            throw new UserNotFoundException("User with email:" + userDTO.getEmail() + " does not exist");
        } else if (buildingEntity.get().getUsers().stream().map(UserEntity::getEmail).toList().contains(userFromRepo.get().getEmail())) {
            throw new UserAlreadyDoThat("User with email: " + userFromRepo.get().getEmail() + " is already user in this building");
        }
        UserEntity userEntity = userFromRepo.get();
        BuildingEntity buildingEntityFromRepo = buildingEntity.get();
        List<UserEntity> users = buildingEntityFromRepo.getUsers();
        if (!userDTO.getApartmentNumber().isEmpty()) {
            userEntity.setApartmentNumber(userDTO.getApartmentNumber());
        }
        users.add(userEntity);
        buildingRepository.save(buildingEntityFromRepo);
        logger.info("User is added to building successfully");
    }

    @Override
    public void removeUser(Long id, Long building_id) {
        if (id == null) {
            throw new UnsupportedOperationException("User id is null");
        }
        Optional<BuildingEntity> buildingEntity = buildingRepository.findById(building_id);
        if (buildingEntity.isPresent()) {
            BuildingEntity currentBuilding = buildingEntity.get();
            List<UserEntity> users = currentBuilding.getUsers();
            for (int i = 0; i < users.size(); i++){
                UserEntity user = users.get(i);
                if (user.getId().equals(id)) {
                    users.remove(i); // remove at index i
                    logger.info("User with first_name: {} and last_name: {} was removed", user.getFirstName(), user.getLastName());

                }

            }
            buildingRepository.save(currentBuilding);
        }
    }

    @Override
    public void addAdmin(UserAdminDTO userAdminDTO) {
        Optional<UserEntity> userFromRepo = userRepository.findByEmail(userAdminDTO.getEmail());
        if (!userAdminDTO.getPassword().equals(Constant.ADMIN_PASSWORD)) {
            throw new UnsupportedOperationException("You don't have permission to do that");
        }
         if (userFromRepo.isEmpty()) {
            throw new UserNotFoundException("User with email:" + userAdminDTO.getEmail() + " does not exist");
        }
        UserEntity userEntity = userFromRepo.get();
        List<UserRoleEntity> currentRoles = userEntity.getRoles();
        long count = currentRoles.stream().filter(r -> r.getRole().equals(UserRoleEnum.ADMIN)).count();
        if (count != 0) {
            throw new UserAlreadyDoThat("User with email: " + userFromRepo.get().getEmail() + " is already admin");
        }
        currentRoles.add(this.userRoleEntityService.findUserRolesByRole(UserRoleEnum.ADMIN));
        userRepository.save(userEntity);
        logger.info("User has role admin successfully");
    }

    @Override
    public Optional<HouseManagerUserDetails> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof  HouseManagerUserDetails houseManagerUserDetails) {
            return Optional.of(houseManagerUserDetails);
        }
        return Optional.empty();
    }

    @Override
    public void updateUser(UserUpdateDto userDTO) {
        Optional<HouseManagerUserDetails> currentUser = getCurrentUser();
        if (currentUser.isEmpty()) {
            throw new UserNotFoundException("User is not present");
        }
        HouseManagerUserDetails houseManagerUserDetailsCurrentUser = currentUser.get();
        boolean haveChanges = checkForChanges(houseManagerUserDetailsCurrentUser, userDTO);

        if (haveChanges) {
            Optional<UserEntity> userByEmail = userRepository.findByEmail(houseManagerUserDetailsCurrentUser.getEmail());
            if (userByEmail.isEmpty()) {
                throw new UserNotFoundException("User is not present in DB");
            }
            UserEntity userEntity = userByEmail.get();
            userEntity.setEmail(userDTO.getEmail());
            userEntity.setFirstName(userDTO.getFirstName());
            userEntity.setLastName(userDTO.getLastName());
            userEntity.setPhoneNumber(userDTO.getPhoneNumber());
            this.userRepository.save(userEntity);
            logger.info("User with id: {} was updated",userEntity.getId());
        }
    }

    private boolean checkForChanges(HouseManagerUserDetails currentUser, UserUpdateDto userDTO) {
        return checkForDiffField(currentUser.getEmail(),userDTO.getEmail())
         || checkForDiffField(currentUser.getFirstName(),userDTO.getFirstName())
                || checkForDiffField(currentUser.getLastName(),userDTO.getLastName())
                || checkForDiffField(currentUser.getPhoneNumber(), userDTO.getPhoneNumber());
    }

    private boolean checkForDiffField(String fieldOfCurrentUser, String fieldOfDTOObject) {
        return !Objects.equals(fieldOfCurrentUser, fieldOfDTOObject);
    }


    private UserEntity map(UserRegistrationDTO userRegistrationDTO) {
        UserEntity mappedEntity = modelMapper.map(userRegistrationDTO, UserEntity.class);
        mappedEntity.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));

        return mappedEntity;
    }
}
