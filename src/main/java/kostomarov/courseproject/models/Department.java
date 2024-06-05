package kostomarov.courseproject.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private int number_employees;

    @Column(name = "director_id")
    @OneToOne
    @JoinColumn(name = "id_employee", nullable = false, updatable = false)
    private Employee employee_id;
}
