package project.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.model.dto.UserModeratorDTO;
import project.model.dto.UserRegistrationDTO;
import project.model.entity.UserEntity;
import project.repositories.UserRepository;
import project.service.UserRoleEntityService;
import project.service.UserService;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleEntityService userRoleEntityService;

    public UserServiceImpl(ModelMapper modelMapper,
                           PasswordEncoder passwordEncoder,
                           UserRepository userRepository, UserRoleEntityService userRoleEntityService) {
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
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
    public void addModerator(UserModeratorDTO userModeratorDTO) {
        Optional<UserEntity> userFromRepo = userRepository.findByEmail(userModeratorDTO.getEmail());
        if (userFromRepo.isPresent()) {
            UserEntity userEntity = userFromRepo.get();
            userEntity.getRoles().add(this.userRoleEntityService.findUserRolesById(3));
            userRepository.save(userEntity);
        }
    }

    private UserEntity map(UserRegistrationDTO userRegistrationDTO) {
        UserEntity mappedEntity = modelMapper.map(userRegistrationDTO, UserEntity.class);
        mappedEntity.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));

        return mappedEntity;
    }
}
