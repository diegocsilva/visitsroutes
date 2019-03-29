package br.com.service;

import br.com.dto.FilesVisitsDTO;
import br.com.model.Employee;
import br.com.model.Store;
import br.com.model.Visit;
import br.com.model.csv.RowCSV;
import br.com.repository.VisitRepository;
import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RequestScoped
public class VisitService {

    @Inject
    private VisitRepository repository;

    @Inject
    private CoordinateService coordinateService;

    @Inject
    private FileService fileService;

    @Inject
    private FileCsvService fileCsvService;

    @Inject
    private EmployeeService employeeService;

    @Inject
    private StoreService storeService;

    public FilesVisitsDTO processFiles(MultipartInput file) {
        FilesVisitsDTO filesVisitsDTO = fileService.createFile(file);
        List<RowCSV> csvEmployees = fileCsvService.buildListRowsByFile(filesVisitsDTO.getFileEmployees());
        List<RowCSV> csvStores = fileCsvService.buildListRowsByFile(filesVisitsDTO.getFileStores());
        List<Employee> employees = employeeService.createEmployeesByListRowCsv(csvEmployees);
        List<Store> stores = storeService.createStoresByListRowCsv(csvStores);
        employeeService.saveAll(employees);
        storeService.saveAll(stores);
        createRoutes(employees, stores);
        return filesVisitsDTO;
    }

    private void createRoutes(List<Employee> employees, List<Store> stores) {
        stores.forEach(store -> {
            List<Visit> visits = sortVisitsByDistances(employees, store);
            int lessVisits = searchFewerVisits(employees);
            int moreVisits = searchMoreVisits(employees);
            int mean = moreVisits != 0 ? moreVisits/lessVisits : 0;

            for (Visit visit : visits) {
                if (visit.getEmployee().getVisits().size() <= mean){
                    Visit clone = visit.clone();
                    Employee employee =
                            employees.stream()
                                    .filter(e -> e.getId().equals(visit.getEmployee().getId()))
                                    .findAny()
                                    .orElse(null);
                    repository.merge(clone);
                    employee.getVisits().add(clone);
                    break;
                }
            }
            visits.clear();
        });
    }

    private int searchFewerVisits(List<Employee> employees) {
        AtomicInteger lessVisits = new AtomicInteger(0);
        employees.forEach(e -> {
            if (e.getVisits() != null && !e.getVisits().isEmpty()){
                if (lessVisits.get() != 0 && lessVisits.get() > e.getVisits().size()){
                    lessVisits.set(e.getVisits().size());
                }
                if (lessVisits.get() == 0)
                    lessVisits.set(e.getVisits().size());
                }
        });
        return lessVisits.get();
    }

    private int searchMoreVisits(List<Employee> employees) {
        AtomicInteger lessVisits = new AtomicInteger(0);
        employees.forEach(e -> {
            if (e.getVisits() != null && !e.getVisits().isEmpty()){
                if (lessVisits.get() != 0 && lessVisits.get() < e.getVisits().size()){
                    lessVisits.set(e.getVisits().size());
                }
                if (lessVisits.get() == 0)
                    lessVisits.set(e.getVisits().size());
            }
        });
        return lessVisits.get();
    }

    private List<Visit> sortVisitsByDistances(List<Employee> employees, Store store) {
        List<Visit>  visits = new ArrayList<>();
        employees.forEach(employee -> visits.add(buildVisit(store, employee)));
        visits.sort(Comparator.comparing(Visit::getDistance));
        return visits;
    }

    private Visit buildVisit(Store store, Employee employee){
        Double distance = coordinateService.distanceCoordinate(store.getCoordinate(), employee.getCoordinate());
        return new Visit(distance, employee, store);
    }
}
