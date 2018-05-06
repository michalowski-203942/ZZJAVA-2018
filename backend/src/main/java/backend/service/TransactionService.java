package backend.service;

import backend.converters.TransactionConverter;
import backend.datastore.dao.CategoryRepository;
import backend.datastore.dao.TransactionRepository;
import backend.datastore.entities.Category;
import backend.datastore.entities.Transaction;
import backend.dto.TransactionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<TransactionInfo> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream()
                .map(TransactionConverter::toTransactionInfo)
                .collect(Collectors.toList());
    }

    public long addTransaction(TransactionInfo transactionInfo){
        Transaction transaction = TransactionConverter.toTransaction(transactionInfo);
        Category category = categoryRepository.getCategoriesByName(transactionInfo.getCategory());
        transaction.setCategory(category);
        return transactionRepository.saveAndFlush(transaction).getId();
    }

    public List<TransactionInfo> getAllTransactionsBetweenDates(Date start, Date end) {

        List<Transaction> transactions = transactionRepository.getAllTransactionsBetweenDates(start,end);
        return transactions.stream()
                .map(TransactionConverter::toTransactionInfo)
                .collect(Collectors.toList());
    }

    public List<TransactionInfo> getAllRevenues(Date start, Date end) {

        List<Transaction> transactions = transactionRepository.getAllRevenues(start,end);
        return transactions.stream()
                .map(TransactionConverter::toTransactionInfo)
                .collect(Collectors.toList());
    }

    public List<TransactionInfo> getAllExpenses(Date start, Date end) {

        List<Transaction> transactions = transactionRepository.getAllExpenses(start,end);
        return transactions.stream()
                .map(TransactionConverter::toTransactionInfo)
                .collect(Collectors.toList());
    }

    public float getAllBalance() {

        float balance = transactionRepository.getAllBalance();
        return balance;
    }

    public float getBalanceBetweenDates(Date start, Date end) {

        float balance = transactionRepository.getBalanceBetweenDates(start,end);
        return balance;
    }
}
