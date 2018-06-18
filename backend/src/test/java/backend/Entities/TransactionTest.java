package backend.Entities;


import backend.dto.TransactionInfo;
import backend.exception.AppException;
import backend.exception.IncorrectParamsException;
import backend.service.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TransactionTest {

    @TestConfiguration
    static class TransactionServiceTestContextConfiguration {

        @Bean
        public TransactionService transactionService() { return new TransactionService(); }
    }

    @Autowired
    private TransactionService service;

    @Test
    public void getAllTransactionsBetweenDates() {

       try{
                TransactionInfo firstTInfo=new TransactionInfo().builder().category("test").date( new Date(2018,10,20)).description("opis").value(100).id(1L).build();
                TransactionInfo secondTInfo=new TransactionInfo().builder().category("test").date(new Date(2018,11,20)).description("opis").value(-100).id(2L).build();
                service.addTransaction(firstTInfo,"tester");
                service.addTransaction(secondTInfo,"tester");
                assertEquals(service.getAllTransactionsBetweenDates("tester",new Date(2018,9,30),new Date(2018,10,30)),1);
       } catch (IncorrectParamsException|AppException e) {
                ResponseEntity.status(400).build();
       }
    }

    @Test
    public void getAllRevenues()  {
        try{
            TransactionInfo firstTInfo=new TransactionInfo().builder().category("test").date( new Date(2018,10,20)).description("opis").value(100).id(1L).build();
            TransactionInfo secondTInfo=new TransactionInfo().builder().category("test").date(new Date(2018,11,20)).description("opis").value(-100).id(2L).build();
            service.addTransaction(firstTInfo,"tester");
            service.addTransaction(secondTInfo,"tester");
            assertEquals(service.getAllRevenues("tester",new Date(2018,9,30),new Date(2018,10,30)),1);
        } catch (IncorrectParamsException|AppException e) {
            ResponseEntity.status(400).build();
        }
    }

    @Test
    public void getAllExpenses() {
        try{
            TransactionInfo firstTInfo=new TransactionInfo().builder().category("test").date( new Date(2018,10,20)).description("opis").value(100).id(1L).build();
            TransactionInfo secondTInfo=new TransactionInfo().builder().category("test").date(new Date(2018,11,20)).description("opis").value(-100).id(2L).build();
            service.addTransaction(firstTInfo,"tester");
            service.addTransaction(secondTInfo,"tester");
            assertEquals(service.getAllExpenses("tester",new Date(2018,9,30),new Date(2018,10,30)),1);
        } catch (IncorrectParamsException|AppException e) {
            ResponseEntity.status(400).build();
        }
    }

    @Test
    public void getAllBalance() {
        try{
            TransactionInfo firstTInfo=new TransactionInfo().builder().category("test").date( new Date(2018,10,20)).description("opis").value(100).id(1L).build();
            TransactionInfo secondTInfo=new TransactionInfo().builder().category("test").date(new Date(2018,11,20)).description("opis").value(-100).id(2L).build();
            service.addTransaction(firstTInfo,"tester");
            service.addTransaction(secondTInfo,"tester");
            assertEquals(service.getAllBalance("tester"),0.0);
        } catch (IncorrectParamsException|AppException e) {
            ResponseEntity.status(400).build();
        }
    }

    @Test
    public void getBalanceBetweenDates() {
        try{
            TransactionInfo firstTInfo=new TransactionInfo().builder().category("test").date( new Date(2018,10,20)).description("opis").value(100).id(1L).build();
            TransactionInfo secondTInfo=new TransactionInfo().builder().category("test").date(new Date(2018,11,20)).description("opis").value(-100).id(2L).build();
            service.addTransaction(firstTInfo,"tester");
            service.addTransaction(secondTInfo,"tester");
            assertEquals(service.getBalanceBetweenDates("tester",new Date(2018,9,30),new Date(2018,10,30)),1);
        } catch (IncorrectParamsException|AppException e) {
            ResponseEntity.status(400).build();
        }
    }

    @Test
    public void deleteTransaction() {
        try{
            TransactionInfo firstTInfo=new TransactionInfo().builder().category("test").date( new Date(2018,10,20)).description("opis").value(100).id(1L).build();
            TransactionInfo secondTInfo=new TransactionInfo().builder().category("test").date(new Date(2018,11,20)).description("opis").value(-100).id(2L).build();
            service.addTransaction(firstTInfo,"tester");
            service.addTransaction(secondTInfo,"tester");
            service.deleteTransaction(1L,"tester");
            assertEquals(service.getAllTransactions("tester"),1);
        } catch (IncorrectParamsException|AppException e) {
            ResponseEntity.status(400).build();
        }
    }

    @Test
    public void getTransaction() {
        try{
            TransactionInfo firstTInfo=new TransactionInfo().builder().category("test").date( new Date(2018,10,20)).description("opis").value(100).id(1L).build();
            service.addTransaction(firstTInfo,"tester");
            service.deleteTransaction(1L,"tester");
            assertEquals(service.getTransactionById(1L),firstTInfo);
        } catch (IncorrectParamsException|AppException e) {
            ResponseEntity.status(400).build();
        }
    }

    @Test
    public void editTransaction() {
        try{
            TransactionInfo firstTInfo=new TransactionInfo().builder().category("test").date( new Date(2018,10,20)).description("opis").value(100).id(1L).build();
            TransactionInfo secondTInfo=new TransactionInfo().builder().category("test").date(new Date(2018,11,20)).description("opis").value(-100).id(1L).build();
            service.addTransaction(firstTInfo,"tester");
            service.editTransaction(secondTInfo);
            assertEquals(service.getTransactionById(1L),secondTInfo);
        } catch (IncorrectParamsException|AppException e) {
            ResponseEntity.status(400).build();
        }
    }
}