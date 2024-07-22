package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.model.entity.BuildingEntity;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long> {

    @Query(
            value = "SELECT b FROM BuildingEntity as b " +
                    "WHERE b.city = ?1" +
                    "AND b.street = ?2" +
                    "AND b.number = ?3")
    BuildingEntity findBuildingEntitiesByAddress(String city, String street, String number);
}
