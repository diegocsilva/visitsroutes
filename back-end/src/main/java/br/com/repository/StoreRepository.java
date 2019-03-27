package br.com.repository;

import br.com.model.Store;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.RequestScoped;
import java.util.List;

@RequestScoped
public class StoreRepository implements PanacheRepositoryBase<Store, Integer> {

    public Store findByName(String name){
        return find("name", name).firstResult();
    }

    public void saveAll(List<Store> stores){
        persist(stores);
    }
}
