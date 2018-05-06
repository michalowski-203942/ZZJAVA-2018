package backend.service;

import backend.converters.TransactionConverter;
import backend.datastore.dao.CategoryRepository;
import backend.datastore.dao.TransactionRepository;
import backend.datastore.entities.Category;
import backend.datastore.entities.Transaction;
import backend.dto.TransactionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
