package project.service;

import project.model.dto.BuildingDTO;

import java.util.List;

public interface BuildingService {

    void addBuilding(BuildingDTO buildingDTO);

    List<BuildingDTO> getAllMyBuildings();
}
