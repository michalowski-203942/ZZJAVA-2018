package backend.Entities;

import backend.dto.NewUser;
import backend.exception.IncorrectParamsException;
import backend.service.RegisterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserTest {
    @TestConfiguration
    static class RegisterServiceTestContextConfiguration {

        @Bean
        public RegisterService registerService() {
            return new RegisterService();
        }
    }

    @Autowired
    private RegisterService service;

    @Test
    public void addNewUserTest() {
        NewUser nu1 = NewUser.builder().username("someone1").password("sth1").build();
        try {
            service.addNewUser(nu1);
            assertEquals(service.getNewUser("someone1").getUsername(), "someone1");
        } catch (IncorrectParamsException e) {
            ResponseEntity.status(400).build();
        }
    }

    @Test
    public void deleteNewUserTest() {
        NewUser nu1 = NewUser.builder().username("someone1").password("sth1").deleted(false).build();
        try {
            service.addNewUser(nu1);
            service.deleteNewUser(nu1.getUsername());
            assertEquals(service.getNewUser("someone1").isDeleted(), true);
        } catch (IncorrectParamsException e) {
            ResponseEntity.status(400).build();
        }
    }

    @Test
    public void checkINewUserIsActiveTest() {
        NewUser nu1 = NewUser.builder().username("someone1").password("sth1").active(false).build();
        try {
            service.addNewUser(nu1);
            service.setNewUserAsActive(nu1.getUsername());
            assertEquals(service.getNewUser("someone1").isActive(), true);
        } catch (IncorrectParamsException e) {
            ResponseEntity.status(400).build();
        }
    }
}
