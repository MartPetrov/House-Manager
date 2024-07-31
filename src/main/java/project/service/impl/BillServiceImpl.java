package project.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import project.model.entity.BillEntity;
import project.model.entity.BuildingEntity;
import project.repositories.BillsRepository;
import project.repositories.BuildingRepository;
import project.service.BillService;

import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImpl implements BillService {

    private final BuildingRepository buildingRepository;
    private final Logger logger = LoggerFactory.getLogger(BillServiceImpl.class);

    public BillServiceImpl(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    @Override
    public void removeBill(Long id, Long building_id) {
        Optional<BuildingEntity> buildingEntity = buildingRepository.findById(building_id);
        if (buildingEntity.isPresent()) {
            BuildingEntity currentBuilding = buildingEntity.get();
            List<BillEntity> bills = currentBuilding.getBills();
            for (int i = 0; i < bills.size(); i++){
                BillEntity bill = bills.get(i);
                if (bill.getId().equals(id)) {
                    bills.remove(i);
                    logger.info("User remove bills with date: {} and number: {}", bill.getDate(), bill.getFirstNumber());
                }

            }
            buildingRepository.save(currentBuilding);
        }
    }
}
