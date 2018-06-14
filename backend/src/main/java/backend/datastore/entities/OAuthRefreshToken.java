package backend.datastore.entities;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "oauth_refresh_token")
public class OAuthRefreshToken {
    @Column(name = "token_id")
    @Id
    private String tokenId;
    private byte[] token;
    private byte[] authentication;
}
