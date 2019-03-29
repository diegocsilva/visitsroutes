package br.com.repository;

import br.com.model.Visit;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class VisitRepository extends BaseRepository<Visit, Integer> implements PanacheRepositoryBase<Visit, Integer> {

    @Transactional
    public void saveAll(List<Visit> visits){
        persist(visits);
    }

    @Transactional
    public void merge(Visit visit) {
        super.merge(visit);
    }
}
