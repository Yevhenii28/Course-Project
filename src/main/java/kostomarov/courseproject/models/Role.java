package kostomarov.courseproject.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    public Role(String name) {
        this.name = name;
    }

}
