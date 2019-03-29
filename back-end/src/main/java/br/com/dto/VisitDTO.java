package br.com.dto;

import br.com.model.Visit;

public class VisitDTO {

    private Double distance;
    private StoreDTO store;

    public VisitDTO() {}

    public VisitDTO(Visit v) {
        this.distance = v.getDistance();
        this.store = new StoreDTO(v.getStore());
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public StoreDTO getStore() {
        return store;
    }

    public void setStore(StoreDTO store) {
        this.store = store;
    }
}
