package project.service.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import project.model.dto.BillDTO;
import project.model.dto.BuildingDTO;
import project.model.entity.BillEntity;
import project.model.entity.BuildingEntity;
import project.model.entity.UserEntity;
import project.repositories.BillsRepository;
import project.repositories.BuildingRepository;
import project.service.BillService;
import project.service.exception.BuildingNotFoundException;
import project.service.exception.UserAlreadyDoThat;
import project.service.exception.UserNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImpl implements BillService {

    private final BillsRepository billsRepository;
    private final BuildingRepository buildingRepository;
    private final ModelMapper modelMapper;
    private final Logger logger = LoggerFactory.getLogger(BillServiceImpl.class);

    public BillServiceImpl(BillsRepository billsRepository, BuildingRepository buildingRepository, ModelMapper modelMapper) {
        this.billsRepository = billsRepository;
        this.buildingRepository = buildingRepository;
        this.modelMapper = modelMapper;
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
                    logger.info("User remove bills with date: {} and number: {}", bill.getDate(), bill.getNoteNumber());
                }

            }
            buildingRepository.save(currentBuilding);
        }
    }

    @Override
    public void addBill(BillDTO billDTO, BuildingDTO buildingDTO) {
        BillEntity currentBillEntity = map(billDTO);
        billsRepository.save(currentBillEntity);
                Optional<BuildingEntity> buildingEntity = buildingRepository.findBuildingEntitiesByAddress(buildingDTO.getCity(), buildingDTO.getStreet(), buildingDTO.getNumber());
        if (buildingEntity.isEmpty()) {
            throw new BuildingNotFoundException("This building is not registered");
        }
        BuildingEntity buildingEntityFromRepo = buildingEntity.get();
        List<BillEntity> bills = buildingEntityFromRepo.getBills();
        bills.add(currentBillEntity);
        buildingRepository.save(buildingEntityFromRepo);
    }

    private BillEntity map(BillDTO billDTO) {
        return modelMapper.map(billDTO, BillEntity.class);
    }
}
