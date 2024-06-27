package kostomarov.courseproject.services;

import kostomarov.courseproject.models.Employee;
import kostomarov.courseproject.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<Employee> getEmployees() {
        return employeeRepository.getEmployees();
    }

    public List<Employee> getEmployeeSorted() {
        return employeeRepository.getEmployeesSorted();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.getEmployeeById(id);
    }

    public Employee getEmployeeBySurname(String surname) {
        return employeeRepository.getEmployeeBySurname(surname);
    }

    public List<Employee> getEmployeesByDepartment(String department) {
        return employeeRepository.getEmployeesByDepartment(department);
    }

    public Employee findEmployeeBySurname(String surname) {
        return employeeRepository.findEmployeeBySurnameStartingWith(surname);
    }

    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void editEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteEmployeeById(Long id) {
        Employee employee = employeeRepository.getReferenceById(id);
        employeeRepository.delete(employee);
    }
}
