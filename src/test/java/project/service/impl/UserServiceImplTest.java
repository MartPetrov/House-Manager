package project.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import project.service.exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BuildingRepository buildingRepository;

    @Mock
    private UserRoleEntityService userRoleEntityService;

    @Mock
    private Logger logger;

    @InjectMocks
    private UserServiceImpl userService;

    private UserEntity userEntity;
    private BuildingEntity buildingEntity;
    private UserRoleEntity userRoleEntity;
    private UserRoleEntity adminRoleEntity;
    private HouseManagerUserDetails userDetails;

    @BeforeEach
    void setUp() {
        userRoleEntity = new UserRoleEntity();
        userRoleEntity.setRole(UserRoleEnum.USER);

        adminRoleEntity = new UserRoleEntity();
        adminRoleEntity.setRole(UserRoleEnum.ADMIN);

        userEntity = new UserEntity();
        userEntity.setEmail("test1@example.com");
        userEntity.setPassword("password");
        userEntity.setFirstName("John");
        userEntity.setLastName("Doe");
        userEntity.setPhoneNumber("1234567890");

        buildingEntity = new BuildingEntity();
        buildingEntity.setCity("City");
        buildingEntity.setStreet("Street");
        buildingEntity.setNumber("1");
        buildingEntity.setUsers(List.of(userEntity));
        buildingEntity.setModerators(List.of(userEntity));


        userDetails = new HouseManagerUserDetails(
                userEntity.getEmail(),
                userEntity.getPassword(),
                List.of(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail(),
                userEntity.getPhoneNumber()
        );
    }

    @Test
    void testRegisterUser() {
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
        userRegistrationDTO.setEmail("test@example.com");
        userRegistrationDTO.setPassword("password");

        when(modelMapper.map(any(UserRegistrationDTO.class), eq(UserEntity.class))).thenReturn(userEntity);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRoleEntityService.findUserRolesByRole(UserRoleEnum.USER)).thenReturn(userRoleEntity);

        userService.registerUser(userRegistrationDTO);

        verify(userRepository, times(1)).save(userEntity);
        assertEquals("encodedPassword", userEntity.getPassword());
    }


    @Test
    void testFindUserByEmail_UserFound() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(userEntity));

        UserEntity foundUser = userService.findUserByEmail("test@example.com");

        assertEquals(userEntity, foundUser);
    }

    @Test
    void testFindUserByEmail_UserNotFound() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userService.findUserByEmail("test@example.com");
        });
    }

    @Test
    void testAddUserInBuilding() {
        UserInBuildingDTO userInBuildingDTO = new UserInBuildingDTO();
        userInBuildingDTO.setEmail("test@example.com");
        userInBuildingDTO.setApartmentNumber("101");

        BuildingDTO buildingDTO = new BuildingDTO();
        buildingDTO.setCity("City");
        buildingDTO.setStreet("Street");
        buildingDTO.setNumber("1");

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(userEntity));
        when(buildingRepository.findBuildingEntitiesByAddress(anyString(), anyString(), anyString())).thenReturn(Optional.of(buildingEntity));
buildingEntity.setUsers(new ArrayList<>());
        userService.addUserInBuilding(userInBuildingDTO, buildingDTO);

        verify(buildingRepository, times(1)).save(buildingEntity);
        assertTrue(buildingEntity.getUsers().contains(userEntity));
        assertEquals("101", userEntity.getApartmentNumber());
    }


    @Test
    void testAddAdmin() {
        UserAdminDTO userAdminDTO = new UserAdminDTO();
        userAdminDTO.setEmail("test@example.com");
        userAdminDTO.setPassword(Constant.ADMIN_PASSWORD);

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(userEntity));
        when(userRoleEntityService.findUserRolesByRole(UserRoleEnum.ADMIN)).thenReturn(adminRoleEntity);

        userService.addAdmin(userAdminDTO);

        verify(userRepository, times(1)).save(userEntity);
        assertTrue(userEntity.getRoles().contains(adminRoleEntity));
    }

    @Test
    void testGetCurrentUser() {
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetails);

        SecurityContextHolder.setContext(securityContext);

        Optional<HouseManagerUserDetails> currentUser = userService.getCurrentUser();

        assertTrue(currentUser.isPresent());
        assertEquals(userDetails, currentUser.get());
    }

    @Test
    void testUpdateUser() {
        UserUpdateDto userUpdateDto = new UserUpdateDto();
        userUpdateDto.setEmail("newemail@example.com");
        userUpdateDto.setFirstName("NewFirstName");
        userUpdateDto.setLastName("NewLastName");
        userUpdateDto.setPhoneNumber("0987654321");

        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(userEntity));

        SecurityContextHolder.setContext(securityContext);

        userService.updateUser(userUpdateDto);

        verify(userRepository, times(1)).save(userEntity);
        assertEquals("newemail@example.com", userEntity.getEmail());
        assertEquals("NewFirstName", userEntity.getFirstName());
        assertEquals("NewLastName", userEntity.getLastName());
        assertEquals("0987654321", userEntity.getPhoneNumber());
    }
}
