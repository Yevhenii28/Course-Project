package kostomarov.courseproject.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_employee", nullable = false, updatable = false)
    private Employee employee;

    @Column(nullable = false)
    private LocalDate pay_date;

    @Column(nullable = false)
    private int base_salary;

    @Column(nullable = false)
    private int bonus;

    @Column(nullable = false)
    private int deductions;
}
