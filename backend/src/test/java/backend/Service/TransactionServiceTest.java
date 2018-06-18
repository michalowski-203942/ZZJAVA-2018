package backend.Service;

import backend.datastore.dao.CategoryRepository;
import backend.datastore.dao.TransactionRepository;
import backend.datastore.dao.UserRepository;
import backend.datastore.entities.Category;
import backend.datastore.entities.Transaction;
import backend.dto.TransactionInfo;
import backend.exception.AppException;
import backend.exception.IncorrectParamsException;
import backend.service.CategoryService;
import backend.service.RegisterService;
import backend.service.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TransactionServiceTest {
    @TestConfiguration
    static class TransactionServiceTestContextConfiguration {

        @Bean
        public TransactionService transactionService() { return new TransactionService(); }
        @Bean
        public RegisterService registerService() { return new RegisterService(); }
        @Bean
        public CategoryService categoryService() {return new CategoryService(); }
    }

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private CategoryService categoryService;

    @MockBean
    private TransactionRepository transactionRepository;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private CategoryRepository categoryRepository;


    @Test
    public void testNumberOfTransactions() {
        int count = transactionService.getAllTransactions("user").size();
        assertEquals(3, count);
    }

    @Test
    public void testDescriptionOfTransactions() {
        List<String> expectedNames = Arrays.asList("des1", "des2", "des3");

        List<String> names =  transactionService.getAllTransactions("user")
                .stream()
                .map(TransactionInfo::getDescription)
                .collect(Collectors.toList());

        assertEquals(expectedNames,names);
    }

    @Test
    public void getAllIncomes() {
        float expected = 100.02f;
        assertEquals(transactionService.getAllIncomes("user").stream().mapToDouble(TransactionInfo::getValue).sum(), expected, 0.001);
    }

    @Test
    public void getAllExpenditures() {
        float expected = -25f;
        assertEquals(transactionService.getAllExpenditures("user").stream().mapToDouble(TransactionInfo::getValue).sum(), expected, 0.001);
    }

    @Test
    public void getAllIncomesFromCategory() {
        float expected = 100.02f;
        assertEquals(transactionService.getAllIncomesFromCategory("user","cat1").stream()
                .mapToDouble(TransactionInfo::getValue).sum(), expected, 0.001);

    }

    @Test
    public void getAllExpendituresFromCategory() {
        float expected = -10f;
        assertEquals(transactionService.getAllExpendituresFromCategory("user","cat1").stream()
                .mapToDouble(TransactionInfo::getValue).sum(), expected, 0.001);
    }

    @Test
    public void getAllTransactionsBetweenDates() {

        float expected = -10f;
        assertEquals(transactionService.getAllTransactionsBetweenDates("user",new Date(2011,10,14),new Date(2011,10,26)).stream()
                .mapToDouble(TransactionInfo::getValue).sum(), expected, 0.001);
    }

    @Test
    public void getAllRevenues()  {
        float expected = 100.02f;
        assertEquals(transactionService.getAllRevenues("user",new Date(2011,10,4),new Date(2011,10,26)).stream()
                .mapToDouble(TransactionInfo::getValue).sum(), expected, 0.001);

    }

    @Test
    public void getAllExpenses() {
        float expected = -10f;
        assertEquals(transactionService.getAllExpenses("user",new Date(2011,10,4),new Date(2011,10,26)).stream()
                .mapToDouble(TransactionInfo::getValue).sum(), expected, 0.001);
    }

    @Test
    public void getAllBalance() {
        float expected = 75.02f;
        assertEquals(transactionService.getAllBalance("user"),expected,0.001);

    }

    @Test
    public void getBalanceBetweenDates() {
            float expected=-25f;
            assertEquals(transactionService.getBalanceBetweenDates("user",new Date(2011,10,15),new Date(2011,11,30)),expected,0.00);

    }



    @Before
    public void init() throws IncorrectParamsException, AppException {
        Category c1 = Category.builder()
                .id(1L)
                .name("cat1")
                .build();
        Category c2 = Category.builder()
                .id(2L)
                .name("cat2")
                .build();
        Transaction t1 = Transaction.builder()
                .id(1L)
                .category(c1)
                .description("des1")
                .value(100.02F)
                .date(new Date(2011,10,10))
                .build();
        Transaction t2 = Transaction.builder()
                .id(2L)
                .category(c1)
                .description("des2")
                .value(-10F)
                .date(new Date(2011,10,20))
                .build();
        Transaction t3 = Transaction.builder()
                .id(3L)
                .category(c2)
                .description("des3")
                .value(-15F)
                .date(new Date(2011,11,1))
                .build();
        Mockito.when(transactionRepository.findAllByUsername("user")).thenReturn(Arrays.asList(t1,t2,t3));
        Mockito.when(transactionRepository.getAllExpendituresFromCategory("user","cat1")).thenReturn(Collections.singletonList(t2));
        Mockito.when(transactionRepository.getAllExpenditures("user")).thenReturn(Arrays.asList(t2,t3));
        Mockito.when(transactionRepository.getAllIncomes("user")).thenReturn(Collections.singletonList(t1));
        Mockito.when(transactionRepository.getAllIncomesFromCategory("user", "cat1")).thenReturn(Collections.singletonList(t1));
        Mockito.when(transactionRepository.getAllTransactionsBetweenDates("user",new Date(2011,10,14),new Date(2011,10,26))).thenReturn(Arrays.asList(t2));
        Mockito.when(transactionRepository.getAllRevenues("user",new Date(2011,10,4),new Date(2011,10,26))).thenReturn(Collections.singletonList(t1));
        Mockito.when(transactionRepository.getAllExpenses("user",new Date(2011,10,4),new Date(2011,10,26))).thenReturn(Collections.singletonList(t2));
        Mockito.when(transactionRepository.getAllBalance("user")).thenReturn((float) 75.02);
        Mockito.when(transactionRepository.getBalanceBetweenDates("user",new Date(2011,10,15),new Date(2011,11,30))).thenReturn(-25f);

    }


}
