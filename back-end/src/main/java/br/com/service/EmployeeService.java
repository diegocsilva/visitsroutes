package br.com.service;

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
    public void save(Employee employee){
        repository.persist(employee);
    }

    public Employee findByName(String name){
        return repository.findByName(name);
    }

    public List<Employee> findAll(){
        return repository.listAll();
    }

    public List<Employee> createEmployeesByListRowCsv(List<RowCSV> csvEmployees) {
        List<Employee> employees = new ArrayList<>();
        csvEmployees.forEach(e -> {
            Employee employee = new Employee(e);
            save(employee);
            employees.add(employee);
        });
        return employees;
    }
}
