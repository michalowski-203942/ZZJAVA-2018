package backend.datastore.dao;

import backend.datastore.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    //pobranie wszystkich przychodow
    @Query("SELECT DISTINCT t FROM User u join u.transactions t WHERE " +
            "u.username = :username AND t.value > 0")
    List<Transaction> getAllIncomes(@Param("username") String username);

    //pobranie wszystkich wydatkow
    @Query("SELECT DISTINCT t FROM User u join u.transactions t  WHERE " +
            "u.username = :username AND t.value < 0")
    List<Transaction> getAllExpenditures(@Param("username") String username);

    //pobranie przychdow z danej kategorii
    @Query("SELECT DISTINCT t FROM User u join u.transactions t  WHERE " +
            "u.username = :username AND t.value > 0 AND t.category.name = :categoryName")
    List<Transaction> getAllIncomesFromCategory(@Param("username") String username,  @Param("categoryName")String categoryName);

    //pobranie wydatków z danej kategorii
    @Query("SELECT DISTINCT t FROM User u join u.transactions t  WHERE " +
            "u.username = :username AND t.value < 0 AND t.category.name = :categoryName ")
    List<Transaction> getAllExpendituresFromCategory(@Param("username") String username, @Param("categoryName")String categoryName);

    @Query("SELECT DISTINCT t FROM User u join u.transactions t WHERE " +
            "u.username = :username AND t.date >= :startDate AND t.date <= :endDate ")
    List<Transaction> getAllTransactionsBetweenDates(@Param("username") String username,  @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT DISTINCT t FROM User u join u.transactions t  WHERE " +
            "u.username = :username AND t.value > 0 AND t.date >= :startDate AND t.date <= :endDate ")
    List<Transaction> getAllRevenues(@Param("username") String username, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT DISTINCT t FROM User u join u.transactions t  WHERE " +
            "u.username = :username AND t.value < 0  AND t.date >= :startDate AND t.date <= :endDate ")
    List<Transaction> getAllExpenses(@Param("username") String username, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT DISTINCT SUM(t.value) FROM User u join u.transactions t  WHERE " +
            "u.username = :username")
    float getAllBalance(@Param("username") String username);

    @Query("SELECT DISTINCT SUM(t.value)  FROM User u join u.transactions t WHERE " +
            "u.username = :username AND t.date >= :startDate AND t.date <= :endDate ")
    float getBalanceBetweenDates(@Param("username") String username, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT DISTINCT t FROM User u join u.transactions t  WHERE " +
            "u.username = :username")
    List<Transaction> findAllByUsername(@Param("username") String username);
    @Query ("DELETE  FROM Transaction t WHERE t.id=:transactionID")
    void deleteId(@Param("transactionID") Long transactionID);
}

