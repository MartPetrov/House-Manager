package project.service.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import project.model.dto.BuildingDTO;
import project.model.entity.BuildingEntity;
import project.repositories.BuildingRepository;
import project.service.BuildingService;
import project.service.exception.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements BuildingService {

    private final ModelMapper modelMapper;
    private final BuildingRepository buildingRepository;
    private final Logger logger = LoggerFactory.getLogger(BuildingServiceImpl.class);

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
        logger.info("Getting all buildings");
        List<BuildingDTO> collect = this.buildingRepository
                .findBuildingEntitiesByUsersId(1)
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public BuildingEntity findBuildingById(long id) {
        List<BuildingDTO> allMyBuildings = getAllMyBuildings();
        Optional<BuildingEntity> byId = this.buildingRepository.findById(id);
        if (byId.isEmpty()) {
            throw new ObjectNotFoundException("Building is missing id: ", id);
        }
        BuildingEntity currentBuilding = byId.get();
        boolean contains = allMyBuildings.stream().anyMatch(buildingDTO -> buildingDTO.getId().equals(id));
        if (!contains) {
            //TODO: add currentUserId
            logger.info("User is trying to access a building that is not his");
            throw new UnsupportedOperationException("""
                    Don't do that!!! :)
                    You are trying to access a building that is not yours""");
        }

        return currentBuilding;
    }

    private BuildingDTO map(BuildingEntity buildingEntity) {
        BuildingDTO newBuilding = new BuildingDTO();
        newBuilding.setCity(buildingEntity.getCity());
        newBuilding.setStreet(buildingEntity.getStreet());
        newBuilding.setNumber(buildingEntity.getNumber());
        newBuilding.setId(buildingEntity.getId());

        return newBuilding;
    }

}
