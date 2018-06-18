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








}