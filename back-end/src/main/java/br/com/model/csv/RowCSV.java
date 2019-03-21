package br.com.model.csv;

public class RowCSV {
    private String name;
    private Double latitude;
    private Double longitude;

    public RowCSV() {
    }

    public RowCSV(String name, Double latitude, Double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public RowCSV(String[] attributes) {
        this.name = attributes[0];
        this.latitude = Double.valueOf(attributes[1]);
        this.longitude = Double.valueOf(attributes[2]);
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