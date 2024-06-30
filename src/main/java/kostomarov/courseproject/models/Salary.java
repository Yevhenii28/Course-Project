package kostomarov.courseproject.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
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

    public String dateConvert() {
        return pay_date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
