package backend.converters;

import backend.datastore.entities.User;
import backend.dto.NewUser;

public class UserConverter {
    public static User toUser(NewUser n){
        return User.builder().username(n.getUsername()).build();
    }
}
