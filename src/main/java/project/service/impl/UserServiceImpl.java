package project.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.model.dto.BuildingDTO;
import project.model.dto.UserModeratorDTO;
import project.model.dto.UserRegistrationDTO;
import project.model.entity.BuildingEntity;
import project.model.entity.UserEntity;
import project.repositories.BuildingRepository;
import project.repositories.UserRepository;
import project.service.UserRoleEntityService;
import project.service.UserService;
import project.service.exception.UserAlreadyDoThat;

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

//    @Override
//    public String importPeople(String firstName, String lastName, String phoneNumber, Integer apartmentNumber) {
//        UserEntity newPeople = new UserEntity(firstName, lastName, apartmentNumber);
//        UserEntity byFirstNameAndSecondName = userRepository.findPeopleByFirst_nameAndSecond_name(firstName, lastName);
//        if (phoneNumber != null && phoneNumber.startsWith("359")) {
//            newPeople.setPhoneNumber(phoneNumber);
//        } else {
//            throw new IllegalArgumentException("Wrong Phone Number");
//        }
//        if (byFirstNameAndSecondName == null) {
//            this.userRepository.save(newPeople);
//            return SUCCESSFUL_IMPORT_PEOPLE + newPeople;
//        }
//        return USER_IS_IN_DATABASE;
//    }

//    @Override
//    public List<UserEntity> findAllPeople() {
//             return   this.userRepository.findAll().stream()
//                        .sorted(Comparator.comparingInt(UserEntity::getApartmentNumber))
//                        .toList();
//    }
//
//    @Override
//    public String findAllPeopleRest() {
//        List<UserEntity> allPeopleCompareApartment =
//                this.userRepository.findAll().stream()
//                .sorted(Comparator.comparingInt(UserEntity::getApartmentNumber))
//                .toList();
//        StringBuilder sb = new StringBuilder();
//        allPeopleCompareApartment.forEach(p -> sb.append(p.toStringRest()));
//        return sb.toString();
//    }
//
//
//
//    @Override
//    public String deleteAllPeople(String password) {
//        if (password.equals(MSP)) {
//            this.userRepository.deleteAll();
//            return DELETE_ALL_PEOPLE_FROM_DB;
//        }
//        return WRONG_PASSWORD;
//    }

    @Override
    public void registerUser(UserRegistrationDTO userRegistration) {
        UserEntity newUser = map(userRegistration);
        newUser.getRoles().add(this.userRoleEntityService.findUserRolesById(1));
        userRepository.save(newUser);
    }

    @Override
    public void addModerator(UserModeratorDTO userModeratorDTO, BuildingDTO buildingDTO) {
        Optional<UserEntity> userFromRepo = userRepository.findByEmail(userModeratorDTO.getEmail());
        Optional<BuildingEntity> buildingEntity = buildingRepository.findBuildingEntitiesByAddress(buildingDTO.getCity(), buildingDTO.getStreet(), buildingDTO.getNumber());
        if (buildingEntity.isPresent()
                && userFromRepo.isPresent()
                && buildingEntity.get().getModerators().contains(userFromRepo.get())) {
            throw new UserAlreadyDoThat("User with email: " + userFromRepo.get().getEmail() + " is already moderator of this building");
        }
        if (userFromRepo.isPresent() && buildingEntity.isPresent()) {
            UserEntity userEntity = userFromRepo.get();
            userEntity.getRoles().add(this.userRoleEntityService.findUserRolesById(3));
            userRepository.save(userEntity);
            BuildingEntity buildingEntityFromRepo = buildingEntity.get();
            List<UserEntity> moderators = buildingEntityFromRepo.getModerators();
            moderators.add(userEntity);
            buildingEntityFromRepo.setModerators(moderators);
            buildingRepository.save(buildingEntityFromRepo);
        }
    }

    private UserEntity map(UserRegistrationDTO userRegistrationDTO) {
        UserEntity mappedEntity = modelMapper.map(userRegistrationDTO, UserEntity.class);
        mappedEntity.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));

        return mappedEntity;
    }
}
