package backend.datastore.entities;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "oauth_access_token")
public class OAuthAccessToken {
    @Column(name = "token_id")
    private String tokenId;
    private byte[] token;
    @Id
    @Column(name = "authentication_id")
    private String authenticationId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "client_id")
    private String clientId;
    private byte[] authentication;
    @Column(name = "refresh_token")
    private String refreshToken;

}
