package kostomarov.courseproject.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class PersonalData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_employee", nullable = false, updatable = false)
    private Employee employee;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date_birth;

    @Column(nullable = false)
    private boolean gender;

    @Column(nullable = false)
    private String address;

    @Column(unique = true)
    private String phone_number;

    @Column(unique = true)
    private String email;
}
