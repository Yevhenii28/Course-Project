package kostomarov.courseproject.models;

import jakarta.persistence.*;
import kostomarov.courseproject.enums.AbsenceTypeConverter;
import kostomarov.courseproject.enums.AbsenceTypeEnum;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
public class Absence {
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

    @Enumerated
    @Column(nullable = false)
    @Convert(converter = AbsenceTypeConverter.class)
    private AbsenceTypeEnum absence_type;

    public String startDateConvert() {
        return start_date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public String endDateConvert() {
        return end_date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public String getAbsenceTypeName() {
        return absence_type.getType();
    }
}
