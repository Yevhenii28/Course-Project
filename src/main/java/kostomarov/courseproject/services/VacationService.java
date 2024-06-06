package kostomarov.courseproject.services;

import kostomarov.courseproject.models.Vacation;
import kostomarov.courseproject.repositories.VacationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VacationService {
    private final VacationRepository vacationRepository;

    public List<Vacation> getVacations() {
        return vacationRepository.getVacations();
    }

    public List<Vacation> getVacationsByEmployeeId(Long id) {
        return vacationRepository.getVacationsByEmployee_Id(id);
    }
}
