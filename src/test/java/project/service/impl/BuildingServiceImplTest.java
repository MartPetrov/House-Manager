package project.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import project.model.dto.BuildingDTO;
import project.model.entity.BuildingEntity;
import project.repositories.BuildingRepository;
import project.service.exception.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BuildingServiceImplTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private BuildingRepository buildingRepository;

    @InjectMocks
    private BuildingServiceImpl buildingService;

    private BuildingDTO buildingDTO;
    private BuildingEntity buildingEntity;

    @BeforeEach
    void setUp() {
        buildingDTO = new BuildingDTO();
        buildingDTO.setId(1L);
        buildingDTO.setCity("City");
        buildingDTO.setStreet("Street");
        buildingDTO.setNumber("123");

        buildingEntity = new BuildingEntity();
        buildingEntity.setId(1L);
        buildingEntity.setCity("City");
        buildingEntity.setStreet("Street");
        buildingEntity.setNumber("123");
    }

    @Test
    void testAddBuilding() {
        when(modelMapper.map(buildingDTO, BuildingEntity.class)).thenReturn(buildingEntity);

        buildingService.addBuilding(buildingDTO);

        verify(buildingRepository, times(1)).save(buildingEntity);
    }

    @Test
    void testGetAllMyBuildings() {
        when(buildingRepository.findBuildingEntitiesByUsersId(1)).thenReturn(List.of(buildingEntity));

        List<BuildingDTO> buildings = buildingService.getAllMyBuildings();

        assertNotNull(buildings);
        assertEquals(1, buildings.size());
        // Instead of stubbing modelMapper here, just verify the contents
        assertEquals(buildingEntity.getId(), buildings.get(0).getId());
        assertEquals(buildingEntity.getCity(), buildings.get(0).getCity());
        assertEquals(buildingEntity.getStreet(), buildings.get(0).getStreet());
        assertEquals(buildingEntity.getNumber(), buildings.get(0).getNumber());
    }

    @Test
    void testFindBuildingById_Success() {
        when(buildingRepository.findById(1L)).thenReturn(Optional.of(buildingEntity));
        when(buildingRepository.findBuildingEntitiesByUsersId(1)).thenReturn(List.of(buildingEntity));

        BuildingEntity result = buildingService.findBuildingById(1L);

        assertNotNull(result);
        assertEquals(buildingEntity, result);
    }

    @Test
    void testFindBuildingById_NotFound() {
        when(buildingRepository.findById(1L)).thenReturn(Optional.empty());

        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () -> {
            buildingService.findBuildingById(1L);
        });

        assertEquals("Building is missing id: ", exception.getMessage());
    }

    @Test
    void testFindBuildingById_NotAuthorized() {
        BuildingEntity otherBuildingEntity = new BuildingEntity();
        otherBuildingEntity.setId(2L);

        when(buildingRepository.findById(1L)).thenReturn(Optional.of(buildingEntity));
        when(buildingRepository.findBuildingEntitiesByUsersId(1)).thenReturn(List.of(otherBuildingEntity));

        UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class, () -> {
            buildingService.findBuildingById(1L);
        });

        assertEquals("Don't do that!!! :)\n" +
                "You are trying to access a building that is not yours", exception.getMessage());
    }
}
