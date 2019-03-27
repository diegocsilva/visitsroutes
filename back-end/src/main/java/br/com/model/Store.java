package br.com.model;

import br.com.model.csv.RowCSV;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
public class Store extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Integer id;

    private String name;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE} )
    private Coordinate coordinate;

    public Store() {
    }

    public Store(RowCSV rowCSV) {
        this.name = rowCSV.getName();
        this.coordinate = new Coordinate(rowCSV.getLatitude(), rowCSV.getLongitude());
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
