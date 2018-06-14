package backend.datastore.dao;

import backend.datastore.entities.Category;
import backend.datastore.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Date;
import java.util.List;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    //pobranie wszystkich przychodow
    @Query("SELECT DISTINCT t FROM Transaction t WHERE " +
            "t.value > 0")
    List<Transaction> getAllIncomes();

    //pobranie wszystkich wydatkow
    @Query("SELECT DISTINCT t FROM Transaction t  WHERE " +
            "t.value < 0")
    List<Transaction> getAllExpenditures();

    //pobranie przychdow z danej kategorii
    @Query("SELECT DISTINCT t FROM Transaction t WHERE " +
            "t.value > 0 AND t.category.name = :categoryName")
    List<Transaction> getAllIncomesFromCategory(@Param("categoryName")String categoryName);

    //pobranie wydatków z danej kategorii
    @Query("SELECT DISTINCT t FROM Transaction t  WHERE " +
            "t.value < 0 AND t.category.name = :categoryName ")
    List<Transaction> getAllExpendituresFromCategory(@Param("categoryName")String categoryName);

    @Query("SELECT DISTINCT t FROM Transaction t  WHERE " +
            "t.date >= :startDate AND t.date <= :endDate ")
    List<Transaction> getAllTransactionsBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT DISTINCT t FROM Transaction t  WHERE " +
            "t.value > 0")
    List<Transaction> getAllRevenues(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT DISTINCT t FROM Transaction t  WHERE " +
            "t.value < 0")
    List<Transaction> getAllExpenses(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT SUM(value) FROM Transaction t")
    float getAllBalance();

    @Query("SELECT SUM(value) FROM Transaction t  WHERE " +
            "t.date >= :startDate AND t.date <= :endDate ")
    float getBalanceBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT DISTINCT t FROM User u join u.transactions t WHERE " +
            "u.username = :username")
    List<Transaction> findAllByUserName(String username);
}
