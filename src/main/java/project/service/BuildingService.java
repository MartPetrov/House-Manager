package project.service;

import project.model.dto.BuildingDTO;
import project.model.entity.BuildingEntity;

import java.util.List;

public interface BuildingService {

    void addBuilding(BuildingDTO buildingDTO);

    List<BuildingDTO> getAllMyBuildings();

    BuildingEntity findBuildingById(long id);
}
