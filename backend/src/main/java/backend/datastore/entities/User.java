package backend.datastore.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "users")
public class User {
    @Id
    private String username;
    private String passwordHash;
    private String token;
    private boolean active;
    private boolean deleted;

    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;
}
