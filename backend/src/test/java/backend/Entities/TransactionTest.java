package backend.Entities;


import backend.datastore.dao.CategoryRepository;
import backend.datastore.dao.TransactionRepository;
import backend.datastore.dao.UserRepository;
import backend.datastore.entities.Category;
import backend.datastore.entities.Transaction;
import backend.dto.CategoryInfo;
import backend.dto.NewUser;
import backend.dto.TransactionInfo;
import backend.exception.AppException;
import backend.exception.IncorrectParamsException;
import backend.service.CategoryService;
import backend.service.RegisterService;
import backend.service.TransactionService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TransactionTest {

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


    @Before
    public void init() throws IncorrectParamsException, AppException {

    }
    @Test
    public void getAllTransactionsBetweenDates() {

       try{
                TransactionInfo firstTInfo=new TransactionInfo().builder().category("test").date( new Date(2018,10,20)).description("opis").value(100).id(1L).build();
                TransactionInfo secondTInfo=new TransactionInfo().builder().category("test").date(new Date(2018,11,20)).description("opis").value(-100).id(2L).build();
                transactionService.addTransaction(firstTInfo,"tester");
                transactionService.addTransaction(secondTInfo,"tester");
                assertEquals(transactionService.getAllTransactionsBetweenDates("tester",new Date(2018,9,30),new Date(2018,10,30)),1);
       } catch (IncorrectParamsException|AppException e) {
                ResponseEntity.status(400).build();
       }
    }

    @Test
    public void getAllRevenues()  {
        try{
            TransactionInfo firstTInfo=new TransactionInfo().builder().category("test").date( new Date(2018,10,20)).description("opis").value(100).id(1L).build();
            TransactionInfo secondTInfo=new TransactionInfo().builder().category("test").date(new Date(2018,11,20)).description("opis").value(-100).id(2L).build();
            transactionService.addTransaction(firstTInfo,"tester");
            transactionService.addTransaction(secondTInfo,"tester");
            assertEquals(transactionService.getAllRevenues("tester",new Date(2018,9,30),new Date(2018,10,30)),1);
        } catch (IncorrectParamsException|AppException e) {
            ResponseEntity.status(400).build();
        }
    }

    @Test
    public void getAllExpenses() {
        try{
            TransactionInfo firstTInfo=new TransactionInfo().builder().category("test").date( new Date(2018,10,20)).description("opis").value(100).id(1L).build();
            TransactionInfo secondTInfo=new TransactionInfo().builder().category("test").date(new Date(2018,11,20)).description("opis").value(-100).id(2L).build();
            transactionService.addTransaction(firstTInfo,"tester");
            transactionService.addTransaction(secondTInfo,"tester");
            assertEquals(transactionService.getAllExpenses("tester",new Date(2018,9,30),new Date(2018,10,30)),1);
        } catch (IncorrectParamsException|AppException e) {
            ResponseEntity.status(400).build();
        }
    }

    @Test
    public void getAllBalance() {
        try{
            TransactionInfo firstTInfo=new TransactionInfo().builder().category("test").date( new Date(2018,10,20)).description("opis").value(100).id(1L).build();
            TransactionInfo secondTInfo=new TransactionInfo().builder().category("test").date(new Date(2018,11,20)).description("opis").value(-100).id(2L).build();
            transactionService.addTransaction(firstTInfo,"tester");
            transactionService.addTransaction(secondTInfo,"tester");
            assertEquals(transactionService.getAllBalance("tester"),0.0);
        } catch (IncorrectParamsException|AppException e) {
            ResponseEntity.status(400).build();
        }
    }

    @Test
    public void getBalanceBetweenDates() {
        try{
            TransactionInfo firstTInfo=new TransactionInfo().builder().category("test").date( new Date(2018,10,20)).description("opis").value(100).id(1L).build();
            TransactionInfo secondTInfo=new TransactionInfo().builder().category("test").date(new Date(2018,11,20)).description("opis").value(-100).id(2L).build();
            transactionService.addTransaction(firstTInfo,"tester");
            transactionService.addTransaction(secondTInfo,"tester");
            assertEquals(transactionService.getBalanceBetweenDates("tester",new Date(2018,9,30),new Date(2018,10,30)),1);
        } catch (IncorrectParamsException|AppException e) {
            ResponseEntity.status(400).build();
        }
    }

    @Test
    public void deleteTransaction() {
        try{
            TransactionInfo firstTInfo= TransactionInfo.builder().category("test").date( new Date(2018,10,20)).description("opis").value(100).build();
            TransactionInfo secondTInfo=new TransactionInfo().builder().category("test").date(new Date(2018,11,20)).description("opis").value(-100).build();
            transactionService.addTransaction(firstTInfo,"tester");
            transactionService.addTransaction(secondTInfo,"tester");
            transactionService.deleteTransaction(1L,"tester");
            assertEquals(transactionService.getAllTransactions("tester"),1);
        } catch (IncorrectParamsException|AppException e) {
            ResponseEntity.status(400).build();
        }
    }

    @Test
    public void getTransaction() {
        try{
            TransactionInfo firstTInfo=new TransactionInfo().builder().category("test").date( new Date(2018,10,20)).description("opis").value(100).id(1L).build();
            transactionService.addTransaction(firstTInfo,"tester");
            transactionService.deleteTransaction(1L,"tester");
            assertEquals(transactionService.getTransactionById(1L),firstTInfo);
        } catch (IncorrectParamsException|AppException e) {
            ResponseEntity.status(400).build();
        }
    }

    @Test
    public void editTransaction() {
        try{
            TransactionInfo firstTInfo=new TransactionInfo().builder().category("test").date( new Date(2018,10,20)).description("opis").value(100).id(1L).build();
            TransactionInfo secondTInfo=new TransactionInfo().builder().category("test").date(new Date(2018,11,20)).description("opis").value(-100).id(1L).build();
            transactionService.addTransaction(firstTInfo,"tester");
            transactionService.editTransaction(secondTInfo);
            assertEquals(transactionService.getTransactionById(1L),secondTInfo);
        } catch (IncorrectParamsException|AppException e) {
            ResponseEntity.status(400).build();
        }
    }



}