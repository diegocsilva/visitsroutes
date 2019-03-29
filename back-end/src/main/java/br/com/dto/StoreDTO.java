package br.com.dto;

import br.com.model.Store;

public class StoreDTO {

    private String name;
    private Double latitude;
    private Double longitude;

    public StoreDTO() {}

    public StoreDTO(Store store) {
        this.name = store.getName();
        this.latitude = store.getCoordinate().getLatitude();
        this.longitude = store.getCoordinate().getLongitude();
    }

    public StoreDTO(String name, Double latitude, Double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
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
}
