package backend.rest;

import backend.dto.NewUser;
import backend.exception.IncorrectParamsException;
import backend.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
public class RegisterRest {
    @Autowired
    private RegisterService registerService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addNewUser(@RequestBody NewUser newUser){
        try {
            registerService.addNewUser(newUser);
            return ResponseEntity.ok().build();
        } catch (IncorrectParamsException e) {
           return ResponseEntity.status(400).build();
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity deleteNewUser(@RequestBody NewUser newUser){
        try {
            registerService.deleteNewUser(newUser.getUsername());
            return ResponseEntity.ok().build();
        } catch (IncorrectParamsException e) {
            return ResponseEntity.status(400).build();
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity setNewUserAsActive(@RequestBody NewUser newUser){
        try {
            registerService.setNewUserAsActive(newUser.getUsername());
            return ResponseEntity.ok().build();
        } catch (IncorrectParamsException e) {
            return ResponseEntity.status(400).build();
        }
    }
}
