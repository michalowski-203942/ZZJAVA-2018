package backend.service;

import backend.converters.TransactionConverter;
import backend.datastore.dao.CategoryRepository;
import backend.datastore.dao.TransactionRepository;
import backend.datastore.dao.UserRepository;
import backend.datastore.entities.Category;
import backend.datastore.entities.Transaction;
import backend.datastore.entities.User;
import backend.dto.TransactionInfo;
import backend.exception.AppException;
import backend.exception.IncorrectParamsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;

    public List<TransactionInfo> getAllTransactions(String username) {
        List<Transaction> transactions = transactionRepository.findAllByUserName(username);
        return transactions.stream()
                .map(TransactionConverter::toTransactionInfo)
                .collect(Collectors.toList());
    }

    public long addTransaction(TransactionInfo transactionInfo, String username) throws IncorrectParamsException, AppException {
        Transaction transaction = TransactionConverter.toTransaction(transactionInfo);
        Category category = categoryRepository.getCategoriesByName(transactionInfo.getCategory());
        transaction.setCategory(category);
        Optional<User> userQuery = userRepository.findById(username);
        if(!userQuery.isPresent()){
            throw new AppException("User does not exists");
        }
        User user = userQuery.get();

        try {
            transactionRepository.saveAndFlush(transaction);
            user.getTransactions().add(transaction);
            userRepository.saveAndFlush(user);
            return transaction.getId();
        } catch (ConstraintViolationException e) {
            throw new IncorrectParamsException("Incorrect transaction data", e);
        }
    }

    //pobranie wszystkich przychodow
    public List<TransactionInfo> getAllIncomes() {

        List<Transaction> transactions = transactionRepository.getAllIncomes();
        return transactions.stream()
                .map(TransactionConverter::toTransactionInfo)
                .collect(Collectors.toList());
    }

    //pobranie wszystkich wydatkow
    public List<TransactionInfo> getAllExpenditures() {

        List<Transaction> transactions = transactionRepository.getAllExpenditures();
        return transactions.stream()
                .map(TransactionConverter::toTransactionInfo)
                .collect(Collectors.toList());
    }

    //pobranie wszystkich przychodow z danej kategorii
    public List<TransactionInfo> getAllIncomesFromCategory(String categoryName) {

        List<Transaction> transactions = transactionRepository.getAllIncomesFromCategory(categoryName);
        return transactions.stream()
                .map(TransactionConverter::toTransactionInfo)
                .collect(Collectors.toList());
    }

    //pobranie wszystkich wydatkow z danej kategorii
    public List<TransactionInfo> getAllExpendituresFromCategory(String categoryName) {

        List<Transaction> transactions = transactionRepository.getAllExpendituresFromCategory(categoryName);
        return transactions.stream()
                .map(TransactionConverter::toTransactionInfo)
                .collect(Collectors.toList());
    }

    //--------------------------

    public List<TransactionInfo> getAllTransactionsBetweenDates(Date start, Date end) {

        List<Transaction> transactions = transactionRepository.getAllTransactionsBetweenDates(start, end);
        return transactions.stream()
                .map(TransactionConverter::toTransactionInfo)
                .collect(Collectors.toList());
    }

    public List<TransactionInfo> getAllRevenues(Date start, Date end) {

        List<Transaction> transactions = transactionRepository.getAllRevenues(start, end);
        return transactions.stream()
                .map(TransactionConverter::toTransactionInfo)
                .collect(Collectors.toList());
    }

    public List<TransactionInfo> getAllExpenses(Date start, Date end) {

        List<Transaction> transactions = transactionRepository.getAllExpenses(start, end);
        return transactions.stream()
                .map(TransactionConverter::toTransactionInfo)
                .collect(Collectors.toList());
    }

    public float getAllBalance() {

        float balance = transactionRepository.getAllBalance();
        return balance;
    }

    public float getBalanceBetweenDates(Date start, Date end) {

        float balance = transactionRepository.getBalanceBetweenDates(start, end);
        return balance;
    }
}
