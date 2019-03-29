package br.com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
public class Visit extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Integer id;

    private Double distance;

    @ManyToOne
    @JoinColumn(name="employee_id", referencedColumnName="id",nullable=false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name="store_id", referencedColumnName="id",nullable=false)
    private Store store;

    @Transient
    private Integer employee_id;

    @Transient
    private Integer store_id;

    public Visit() {
    }

    public Visit(Double distance, Employee employee, Store store) {
        this.distance = distance;
        this.employee = employee;
        this.store = store;
    }

    public Visit(Double distance, Integer employee, Integer store) {
        this.distance = distance;
        this.employee_id = employee;
        this.store_id = store;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public Integer getStore_id() {
        return store_id;
    }

    public void setStore_id(Integer store_id) {
        this.store_id = store_id;
    }

    public Visit clone(){
        Visit clone = new Visit();
        clone.setDistance(this.distance);
        clone.setEmployee(this.employee);
        clone.setStore(this.store);
        return clone;
    }
}
