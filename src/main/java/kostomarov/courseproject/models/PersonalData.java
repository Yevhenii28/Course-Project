package kostomarov.courseproject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import kostomarov.courseproject.enums.FamilyStateConverter;
import kostomarov.courseproject.enums.FamilyStateEnum;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
public class PersonalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_employee", nullable = false, updatable = false)
    private Employee employee;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date_birth;

    @Column(nullable = false)
    @ColumnDefault(value = "1")
    private boolean gender;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, unique = true)
    private String phone_number;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Convert(converter = FamilyStateConverter.class)
    private FamilyStateEnum familyState;

    @Column(nullable = false)
    @ColumnDefault(value = "0")
    @Min(value = 0)
    private int numChildren;

    public String genderConvert() {
        if (gender) {
            return "Чоловіча";
        }
        return "Жіноча";
    }

    public String dateConvert() {
        return date_birth.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public String getFamilyStateName() {
        if (gender && !familyState.getState().equals("Цивільний шлюб")) {
            return familyState.getState().concat("ий");
        } else if (!gender && !familyState.getState().equals("Цивільний шлюб")) {
            return familyState.getState().concat("а");
        }
        return familyState.getState();
    }
}
