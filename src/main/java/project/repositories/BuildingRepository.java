package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.model.entity.BuildingEntity;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long> {

    @Query(
            value = "SELECT b FROM BuildingEntity as b " +
                    "WHERE b.address = ?1")
    BuildingEntity findBuildingEntitiesByAddress(String address);
}
