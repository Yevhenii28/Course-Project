package kostomarov.courseproject.services;

import kostomarov.courseproject.models.Salary;
import kostomarov.courseproject.repositories.SalaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaryService {
    private final SalaryRepository salaryRepository;

    public List<Salary> getSalaries() {
        return salaryRepository.getAllSalaries();
    }

    public List<Salary> getSalariesByEmployeeId(Long id) {
        return salaryRepository.getSalariesByEmployee_Id(id);
    }
}
