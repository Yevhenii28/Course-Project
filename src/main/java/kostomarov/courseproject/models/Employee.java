package kostomarov.courseproject.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String pname;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate hire_date;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private String department;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Employee employee = (Employee) obj;

        if (!surname.equals(employee.getSurname())) return false;
        if (!name.equals(employee.getName())) return false;
        if (!pname.equals(employee.getPname())) return false;
        if (!hire_date.equals(employee.getHire_date())) return false;
        if (!position.equals(employee.getPosition())) return false;
        return department == employee.getDepartment();
    }

    public String dateConvert() {
        return hire_date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
