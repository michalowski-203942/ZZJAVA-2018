package backend.datastore.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "transactions")
@SequenceGenerator(name="seq_t", initialValue=1000)
public class Transaction {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_t")
    private Long id;

    @NotBlank
    private String description;

    @NotNull
    private float value;

    @NotNull
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @ManyToOne
    private Category category;

}
