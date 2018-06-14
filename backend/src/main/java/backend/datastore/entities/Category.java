package backend.datastore.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "category")
@SequenceGenerator(name = "seq_c", initialValue = 1000)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_c")
    private Long id;

    @NotBlank
    private String name;
}
