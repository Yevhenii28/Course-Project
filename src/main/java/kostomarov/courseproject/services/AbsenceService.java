package kostomarov.courseproject.services;

import kostomarov.courseproject.models.Absence;
import kostomarov.courseproject.repositories.AbsenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AbsenceService {
    private final AbsenceRepository absenceRepository;

    public List<Absence> getAbsences() {
        return absenceRepository.getAbsences();
    }

    public List<Absence> getAbsencesSorted() {
        return absenceRepository.getAbsencesSorted();
    }

    public List<Absence> getAbsencesByEmployeeId(Long id) {
        return absenceRepository.getAbsencesByEmployee_Id(id);
    }

    public List<Absence> getAbsencesByEmployeeSurname(String surname) {
        return absenceRepository.getAbsenceByEmployee_Surname(surname);
    }

    public List<Absence> getAbsencesByDate(int year) {
        return absenceRepository.getAbsencesByDateYear(year);
    }

    public void addAbsence(Absence absence) {
        absenceRepository.save(absence);
    }
}
