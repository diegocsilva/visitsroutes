package br.com.service;

import br.com.dto.VisitDTO;
import org.apache.commons.io.FileUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

@RequestScoped
public class VisitService {

    @Inject
    private CoordinateService coordinateService;

    public Object processFiles(VisitDTO visitDTO){
        try {
            String employeesFile = visitDTO.getFileEmployees();
            byte[] bytes = Base64.getDecoder().decode(employeesFile);
            FileUtils.writeByteArrayToFile(new File("/home/diego/pathname.csv"), bytes);

            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
