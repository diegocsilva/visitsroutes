package br.com.repository;

import br.com.model.Employee;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class EmployeeRepository implements PanacheRepositoryBase<Employee, Integer> {

    public Employee findByName(String name){
        return find("name", name).firstResult();
    }
}
