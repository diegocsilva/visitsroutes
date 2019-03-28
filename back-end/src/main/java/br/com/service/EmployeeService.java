package br.com.service;

import br.com.dto.EmployeeDTO;
import br.com.model.Employee;
import br.com.model.csv.RowCSV;
import br.com.repository.EmployeeRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class EmployeeService {

    @Inject
    private EmployeeRepository repository;

    @Transactional
    public void save(Employee employee) {
        repository.persist(employee);
    }

    public Employee findByName(String name) {
        return repository.findByName(name);
    }

    public List<EmployeeDTO> findAll() {
        List<Employee> employees = repository.listAll();
        List<EmployeeDTO> employeeDTO = new ArrayList<>();
        employees.forEach(e -> employeeDTO.add(new EmployeeDTO(e)));
        return employeeDTO;
    }

    public List<Employee> createEmployeesByListRowCsv(List<RowCSV> csvEmployees) {
        List<Employee> employees = new ArrayList<>();
        csvEmployees.forEach(e -> employees.add(new Employee(e)));
        return employees;
    }

    @Transactional
    public void saveAll(List<Employee> employees) {
        repository.saveAll(employees);
    }
}
