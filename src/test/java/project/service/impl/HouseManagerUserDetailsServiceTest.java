package project.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import project.model.entity.UserEntity;
import project.model.entity.UserRoleEntity;
import project.model.enums.UserRoleEnum;
import project.model.user.HouseManagerUserDetails;
import project.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HouseManagerUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private HouseManagerUserDetailsService userDetailsService;

    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        UserRoleEntity roleUser = new UserRoleEntity();
        roleUser.setRole(UserRoleEnum.USER);
        UserRoleEntity roleAdmin = new UserRoleEntity();
        roleAdmin.setRole(UserRoleEnum.ADMIN);

        userEntity = new UserEntity();
        userEntity.setEmail("test@example.com");
        userEntity.setPassword("password");
        userEntity.setFirstName("John");
        userEntity.setLastName("Doe");
        userEntity.setPhoneNumber("1234567890");
        userEntity.setRoles(List.of(roleUser, roleAdmin));
    }

    @Test
    void testLoadUserByUsername_UserFound() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(userEntity));

        UserDetails userDetails = userDetailsService.loadUserByUsername("test@example.com");

        assertNotNull(userDetails);
        assertEquals(userEntity.getEmail(), userDetails.getUsername());
        assertEquals(userEntity.getPassword(), userDetails.getPassword());
        assertEquals(2, userDetails.getAuthorities().size());
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());

        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername("test@example.com");
        });

        assertEquals("User with email test@example.com not found!", exception.getMessage());
    }
}
