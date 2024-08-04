package project.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import project.model.entity.UserRoleEntity;
import project.model.enums.UserRoleEnum;
import project.repositories.UserRoleEntityRepository;
import project.service.UserRoleEntityService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserRoleEntityServiceImplTest {

    @Mock
    private UserRoleEntityRepository userRoleEntityRepository;

    @InjectMocks
    private UserRoleEntityServiceImpl userRoleEntityService;

    private UserRoleEntity roleUser;

    @BeforeEach
    void setUp() {
        roleUser = new UserRoleEntity();
        roleUser.setRole(UserRoleEnum.USER);
    }

    @Test
    void testFindUserRolesByRole() {
        when(userRoleEntityRepository.findByRole(UserRoleEnum.USER)).thenReturn(roleUser);

        UserRoleEntity result = userRoleEntityService.findUserRolesByRole(UserRoleEnum.USER);

        assertNotNull(result);
        assertEquals(UserRoleEnum.USER, result.getRole());
        verify(userRoleEntityRepository, times(1)).findByRole(UserRoleEnum.USER);
    }

    // Additional tests can be added for other scenarios
}
