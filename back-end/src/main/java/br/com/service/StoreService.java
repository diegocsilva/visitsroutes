package br.com.service;

import br.com.model.Store;
import br.com.model.csv.RowCSV;
import br.com.repository.StoreRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class StoreService {

    @Inject
    private StoreRepository repository;

    @Transactional
    public void save(Store store) {
        repository.persist(store);
    }

    public Store findByName(String name) {
        return repository.findByName(name);
    }

    public List<Store> findAll() {
        return repository.listAll();
    }

    public List<Store> createStoresByListRowCsv(List<RowCSV> csvStores) {
        List<Store> stores = new ArrayList<>();
        csvStores.forEach(s -> stores.add(new Store(s)));
        return stores;
    }

    @Transactional
    public void saveAll(List<Store> stores) {
        repository.saveAll(stores);
    }
}
