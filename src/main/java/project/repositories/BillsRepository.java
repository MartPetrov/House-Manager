package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.entity.Bill;

import java.util.List;

@Repository
public interface BillsRepository extends JpaRepository<Bill, Long> {
    @Override
    List<Bill> findAll();

//    @Query(value = "SELECT * FROM Bills b WHERE b.month = :month limit 1;",nativeQuery = true)
//    Bill findBillByMonth(int month);
}
