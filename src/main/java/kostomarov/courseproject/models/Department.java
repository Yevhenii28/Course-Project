package kostomarov.courseproject.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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
    @ColumnDefault(value = "0")
    private int number_employees;

    @OneToOne
    @JoinColumn(name = "id_director", nullable = false, updatable = false)
    private Employee employee_id;
}
