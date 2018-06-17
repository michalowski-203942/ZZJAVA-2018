package backend.service;

import backend.converters.UserConverter;
import backend.datastore.dao.UserRepository;
import backend.datastore.entities.User;
import backend.dto.NewUser;
import backend.exception.IncorrectParamsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

@Service
public class RegisterService {
    @Autowired
    private UserRepository repository;

    public void addNewUser(NewUser newUser) throws IncorrectParamsException {
        if(repository.findById(newUser.getUsername()).isPresent()){
            throw new IncorrectParamsException("Incorrect username");
        }
        User user = UserConverter.toUser(newUser);
        user.setActive(true);
        user.setDeleted(false);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passHash = passwordEncoder.encode(newUser.getPassword());
        user.setPasswordHash(passHash);
        try {
            repository.saveAndFlush(user);
        } catch (ConstraintViolationException e) {
            throw new IncorrectParamsException("Incorrect user data", e);
        }
    }

    public void deleteNewUser(String username) throws IncorrectParamsException {
        Optional<User> u = repository.findById(username);
        if(u.isPresent()){
            u.get().setDeleted(true);
        } else {
            throw new IncorrectParamsException("Incorrect username");
        }
    }

    public void setNewUserAsActive(String username) throws IncorrectParamsException {
        Optional<User> u = repository.findById(username);
        if(u.isPresent()){
            u.get().setActive(true);
        } else {
            throw new IncorrectParamsException("Incorrect username");
        }
    }

    public NewUser getNewUser(String username) throws IncorrectParamsException {
        Optional<User> u = repository.findById(username);
        if(u.isPresent()){
            User user = u.get();
            return UserConverter.toNewUser(user);
        } else {
            throw new IncorrectParamsException("Incorrect username");
        }
    }
}
