package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.model.entity.BillEntity;

import java.util.List;

@Repository
public interface BillsRepository extends JpaRepository<BillEntity, Long> {
    @Override
    List<BillEntity> findAll();

//    @Query(value = "SELECT * FROM Bills b WHERE b.month = :month limit 1;",nativeQuery = true)
//    Bill findBillByMonth(int month);
}
