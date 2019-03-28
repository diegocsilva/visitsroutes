package br.com.dto;

import br.com.model.Employee;
import br.com.model.Store;
import br.com.model.Visit;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDTO {
    private Integer id;
    private String name;
    private Double latitude;
    private Double longitude;
    private Integer visits;
    private List<Visit> visitsList;

    public EmployeeDTO() {}

    public EmployeeDTO(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.latitude = employee.getCoordinate().getLatitude();
        this.longitude = employee.getCoordinate().getLongitude();
        this.visitsList = employee.getVisits();
        this.visitsList = new ArrayList<>();
        this.visits = employee.getVisits().size();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getVisits() {
        return visits;
    }

    public void setVisits(Integer visits) {
        this.visits = visits;
    }

    public List<Visit> getVisitsList() {
        return visitsList;
    }

    public void setVisitsList(List<Visit> visitsList) {
        this.visitsList = visitsList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
