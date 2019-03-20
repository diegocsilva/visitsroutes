package br.com.service;

import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class VisitService {

    @Inject
    private CoordinateService coordinateService;

    @Inject
    private FileUploadService fileUploadService;

    public Object processFiles(MultipartInput file) {
        List<String> result = fileUploadService.createFile(file);
        return result;
    }
}
