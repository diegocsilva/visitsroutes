package br.com.service;

import br.com.dto.VisitDTO;
import br.com.model.Employee;
import br.com.model.Store;
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

    public VisitDTO processFiles(MultipartInput file) {
        VisitDTO visitDTO = fileService.createFile(file);
        List<RowCSV> csvEmployees = fileCsvService.buildListRowsByFile(visitDTO.getFileEmployees());
        List<RowCSV> csvStores = fileCsvService.buildListRowsByFile(visitDTO.getFileStores());
        List<Employee> employees = employeeService.createEmployeesByListRowCsv(csvEmployees);
        List<Store> stores = storeService.createStoresByListRowCsv(csvStores);
        employeeService.saveAll(employees);
        storeService.saveAll(stores);

        return visitDTO;
    }
}
