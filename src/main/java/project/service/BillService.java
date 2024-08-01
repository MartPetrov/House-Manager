package project.service;


import project.model.dto.BillDTO;
import project.model.dto.BuildingDTO;

public interface BillService {

    void removeBill(Long id, Long building_id);

    void addBill(BillDTO billDTO, BuildingDTO buildingDTO);
}
