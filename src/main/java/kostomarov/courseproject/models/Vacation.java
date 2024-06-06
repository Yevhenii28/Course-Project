package kostomarov.courseproject.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
public class Vacation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_employee", nullable = false, updatable = false)
    private Employee employee;

    @Column(nullable = false)
    private LocalDate start_date;

    @Column(nullable = false)
    private LocalDate end_date;

    @Column(nullable = false)
    private String vacation_type;

    public String startDateConvert() {
        return start_date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public String endDateConvert() {
        return end_date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
