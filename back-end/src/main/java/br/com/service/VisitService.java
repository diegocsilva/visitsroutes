package br.com.service;

import br.com.dto.FilesVisitsDTO;
import br.com.model.Employee;
import br.com.model.Store;
import br.com.model.Visit;
import br.com.model.csv.RowCSV;
import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class VisitService {

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
            Employee employee = analyzeBetterRoute(employees, store);
        });
    }

    private Employee analyzeBetterRoute(List<Employee> employees, Store store) {
        return null;
    }
}
