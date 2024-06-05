package kostomarov.courseproject.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate hire_date;

    @ManyToOne
    @JoinColumn(name="position_id", nullable = false, updatable = false)
    private Position position;

    @ManyToOne
    @JoinColumn(name="department_id", nullable = false, updatable = false)
    private Department department_id;

    @Column(nullable = false)
    private int salary;
}
