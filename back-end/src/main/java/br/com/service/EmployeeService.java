package br.com.service;

import br.com.model.Employee;
import br.com.repository.EmployeeRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class EmployeeService {

    @Inject
    EmployeeRepository repository;

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
}
