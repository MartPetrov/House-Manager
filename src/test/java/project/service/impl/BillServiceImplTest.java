package project.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import project.model.dto.BillDTO;
import project.model.dto.BuildingDTO;
import project.model.entity.BillEntity;
import project.model.entity.BuildingEntity;
import project.repositories.BillsRepository;
import project.repositories.BuildingRepository;
import project.service.exception.BuildingNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BillServiceImplTest {

    @Mock
    private BillsRepository billsRepository;

    @Mock
    private BuildingRepository buildingRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private BillServiceImpl billService;

    private BillDTO billDTO;
    private BillEntity billEntity;
    private BuildingDTO buildingDTO;
    private BuildingEntity buildingEntity;

    @BeforeEach
    void setUp() {
        billDTO = new BillDTO();
        billDTO.setId(1L);
        billDTO.setNoteNumber("123");
        billDTO.setDate("2023-08-01");

        billEntity = new BillEntity();
        billEntity.setId(1L);
        billEntity.setNoteNumber("123");
        billEntity.setDate("2023-08-01");

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
        buildingEntity.setBills(new ArrayList<>());
    }

    @Test
    void testRemoveBill() {
        buildingEntity.getBills().add(billEntity);
        when(buildingRepository.findById(1L)).thenReturn(Optional.of(buildingEntity));

        billService.removeBill(1L, 1L);

        verify(buildingRepository, times(1)).save(buildingEntity);
        assertTrue(buildingEntity.getBills().isEmpty());
    }

    @Test
    void testAddBill_Success() {
        when(modelMapper.map(billDTO, BillEntity.class)).thenReturn(billEntity);
        when(buildingRepository.findBuildingEntitiesByAddress("City", "Street", "123")).thenReturn(Optional.of(buildingEntity));

        billService.addBill(billDTO, buildingDTO);

        verify(billsRepository, times(1)).save(billEntity);
        verify(buildingRepository, times(1)).save(buildingEntity);
        assertTrue(buildingEntity.getBills().contains(billEntity));
    }

    @Test
    void testAddBill_BuildingNotFound() {
        when(modelMapper.map(billDTO, BillEntity.class)).thenReturn(billEntity);
        when(buildingRepository.findBuildingEntitiesByAddress("City", "Street", "123")).thenReturn(Optional.empty());

        BuildingNotFoundException exception = assertThrows(BuildingNotFoundException.class, () -> {
            billService.addBill(billDTO, buildingDTO);
        });

        assertEquals("This building is not registered", exception.getMessage());
    }

}
