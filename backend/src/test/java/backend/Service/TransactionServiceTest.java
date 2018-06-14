package backend.Service;

import backend.datastore.dao.TransactionRepository;
import backend.datastore.entities.Category;
import backend.datastore.entities.Transaction;
import backend.dto.TransactionInfo;
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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TransactionServiceTest {
    @TestConfiguration
    static class TransactionServiceTesttContextConfiguration {

        @Bean
        public TransactionService transactionService() {
            return new TransactionService();
        }
    }

    @Autowired
    private TransactionService service;

    @MockBean
    private TransactionRepository transactionRepository;


    @Test
    public void testNumberOfTransactions() {
        int count = service.getAllTransactions("user").size();
        assertEquals(3, count);
    }

    @Test
    public void testDescriptionOfTransactions() {
        List<String> expectedNames = Arrays.asList("des1", "des2", "des3");

        List<String> names =  service.getAllTransactions("user")
                .stream()
                .map(TransactionInfo::getDescription)
                .collect(Collectors.toList());

        assertEquals(expectedNames,names);
    }


    @Before
    public void init(){
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
                .build();
        Transaction t2 = Transaction.builder()
                .id(2L)
                .category(c1)
                .description("des2")
                .value(-10F)
                .build();
        Transaction t3 = Transaction.builder()
                .id(3L)
                .category(c2)
                .description("des3")
                .value(-15F)
                .build();
        Mockito.when(transactionRepository.findAllByUserName("user")).thenReturn(Arrays.asList(t1,t2,t3));
    }


}
