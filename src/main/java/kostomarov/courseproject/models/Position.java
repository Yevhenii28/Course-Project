package kostomarov.courseproject.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String salary_range;
}
