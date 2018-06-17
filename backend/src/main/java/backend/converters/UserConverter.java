package backend.converters;

import backend.datastore.entities.User;
import backend.dto.NewUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserConverter {
    public static User toUser(NewUser n){

        return User.builder().username(n.getUsername()).build();
    }

    public static NewUser toNewUser(User n) {
        return NewUser.builder().username(n.getUsername()).deleted(n.isDeleted()).active(n.isActive()).build();
    }
}
