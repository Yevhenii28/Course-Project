package kostomarov.courseproject.services;

import kostomarov.courseproject.models.Department;
import kostomarov.courseproject.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.getDepartments();
    }

    public Department getDepartmentByName(String name) {
        return departmentRepository.getDepartmentByName(name);
    }

    public void editDepartment(Department department) {
        departmentRepository.save(department);
    }
}
