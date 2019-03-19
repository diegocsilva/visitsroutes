package br.com.service;

import br.com.model.Coordinate;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class CoordinateService {

    private final Integer EARTH_TOTAL_RADIUS = 6371;

    public Double distanceCoordinate(Coordinate coordinateA, Coordinate coordinateB) {
        double latA = coordinateA.getLatitude();
        double longA = coordinateA.getLongitude();
        double latB = coordinateB.getLatitude();
        double longB = coordinateB.getLongitude();

        double dLat = Math.toRadians(latB - latA);
        double dLon = Math.toRadians(longB - longA);

        latA = Math.toRadians(latA);
        latB = Math.toRadians(latB);

        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(latA) *
                        Math.cos(latB);

        double rad = EARTH_TOTAL_RADIUS;
        double c = 2 * Math.asin(Math.sqrt(a));

        return rad * c;
    }
}
