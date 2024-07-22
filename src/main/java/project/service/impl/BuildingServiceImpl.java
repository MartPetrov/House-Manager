package project.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.model.dto.BuildingDTO;
import project.model.entity.BuildingEntity;
import project.repositories.BuildingRepository;
import project.service.BuildingService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements BuildingService {

    private final ModelMapper modelMapper;
    private final BuildingRepository buildingRepository;

    public BuildingServiceImpl(ModelMapper modelMapper, BuildingRepository buildingRepository) {
        this.modelMapper = modelMapper;
        this.buildingRepository = buildingRepository;
    }

    @Override
    public void addBuilding(BuildingDTO buildingDTO) {
        buildingRepository.save(map(buildingDTO));
    }

    private BuildingEntity map(BuildingDTO buildingDTO) {
        return modelMapper.map(buildingDTO, BuildingEntity.class);
    }

    @Override
    public List<BuildingDTO> getAllMyBuildings() {
      return  this.buildingRepository
                .findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    private BuildingDTO map(BuildingEntity buildingEntity) {
        BuildingDTO newBuilding = new BuildingDTO();
        newBuilding.setCity(buildingEntity.getCity());
        newBuilding.setStreet(buildingEntity.getStreet());
        newBuilding.setNumber(buildingEntity.getNumber());

        return newBuilding;
    }

}
