package br.com.model;

import br.com.model.csv.RowCSV;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
public class Employee extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Integer id;
    private String name;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE} )
    private Coordinate coordinate;

    public Employee() {
    }

    public Employee(String name, Coordinate coordinate) {
        this.name = name;
        this.coordinate = coordinate;
    }

    public Employee(RowCSV e) {
        this.name = e.getName();
        this.coordinate = new Coordinate(e.getLatitude(), e.getLongitude());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
