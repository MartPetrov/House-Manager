package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.model.entity.BuildingEntity;
import project.model.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long> {

    @Query( nativeQuery = true,
            value = "SELECT b.* FROM buildings as b WHERE b.city = ?1 AND b.street = ?2 AND b.number = ?3")
    Optional<BuildingEntity> findBuildingEntitiesByAddress(String city, String street, String number);


    @Query ( nativeQuery = true,
            value = "SELECT b.* FROM buildings as b, buildings_users as bu WHERE bu.users_id = ?1 and b.id = bu.building_entity_id"

    )
    List<BuildingEntity> findBuildingEntitiesByUsersId(long userId);
}
