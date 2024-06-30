package kostomarov.courseproject.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.*;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_employee", nullable = false, updatable = false)
    private Employee employee;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime in_time;

    @Column(nullable = false)
    private LocalTime out_time;

    public String dateConvert() {
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
