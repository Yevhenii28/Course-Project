package kostomarov.courseproject.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private int number_employees;

    @OneToOne
    @JoinColumn(name = "id_director", nullable = false, updatable = false)
    private Employee employee_id;
}
