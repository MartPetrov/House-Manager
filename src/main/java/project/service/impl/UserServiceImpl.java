package project.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.model.dto.BuildingDTO;
import project.model.dto.UserInBuildingDTO;
import project.model.dto.UserModeratorDTO;
import project.model.dto.UserRegistrationDTO;
import project.model.entity.BuildingEntity;
import project.model.entity.UserEntity;
import project.model.enums.UserRoleEnum;
import project.repositories.BuildingRepository;
import project.repositories.UserRepository;
import project.service.UserRoleEntityService;
import project.service.UserService;
import project.service.exception.BuildingNotFoundException;
import project.service.exception.UserAlreadyDoThat;
import project.service.exception.UserNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final BuildingRepository buildingRepository;
    private final UserRoleEntityService userRoleEntityService;

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
        if (!userDTO.getApartmentNumber().isEmpty())  {
            userEntity.setApartmentNumber(userDTO.getApartmentNumber());
        }
        users.add(userEntity);
        buildingRepository.save(buildingEntityFromRepo);
    }


    private UserEntity map(UserRegistrationDTO userRegistrationDTO) {
        UserEntity mappedEntity = modelMapper.map(userRegistrationDTO, UserEntity.class);
        mappedEntity.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));

        return mappedEntity;
    }
}
