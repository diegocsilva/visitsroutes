package br.com.repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public abstract class BaseRepository<T, Y> {

    @Inject
    private EntityManager entityManager;

    public void merge(T entity){
        entityManager.merge(entity);
    }

    public void flush(){
        entityManager.flush();
    }
}
