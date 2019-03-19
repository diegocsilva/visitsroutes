package br.com.repository;

import br.com.model.Store;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class StoreRepository implements PanacheRepositoryBase<Store, Integer> {

    public Store findByName(String name){
        return find("name", name).firstResult();
    }
}
