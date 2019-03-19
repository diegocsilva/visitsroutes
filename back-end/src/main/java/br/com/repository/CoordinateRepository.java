package br.com.repository;

import br.com.model.Coordinate;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class CoordinateRepository implements PanacheRepositoryBase<Coordinate, Integer> {

}
