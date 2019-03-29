package br.com.model;

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

    public Visit() {
    }

    public Visit(Double distance, Employee employee, Store store) {
        this.distance = distance;
        this.employee = employee;
        this.store = store;
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

    public Visit clone(){
        Visit clone = new Visit();
        clone.setDistance(this.distance);
        clone.setEmployee(this.employee);
        clone.setStore(this.store);
        return clone;
    }
}
