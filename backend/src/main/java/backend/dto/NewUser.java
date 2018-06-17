package backend.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class NewUser {
    private String username;
    private String password;
    private boolean deleted;
    private boolean active;
}
