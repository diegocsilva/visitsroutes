package br.com.service;

import br.com.dto.VisitDTO;
import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;
import org.jboss.resteasy.plugins.providers.multipart.MultipartInputImpl;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedMap;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicReference;

@RequestScoped
public class FileService {

    private static final String UNKNOWN = "UNKNOWN";
    private static final String FILENAME = "filename";
    private static final String NAME = "name";
    private static final String EMPLOYEES = "employees";
    private static final String STORES = "stores";
    private static final String HOME_DIR = System.getProperty("user.home");

    public VisitDTO createFile(MultipartInput input) {
        AtomicReference<VisitDTO> visitDTO = new AtomicReference<>();
        visitDTO.set(new VisitDTO());

        input.getParts().forEach(inputPart -> {
            try {
                MultivaluedMap<String, String> header = inputPart.getHeaders();
                String fileName = this.getFileByValue(header, FILENAME);
                String idName = this.getFileByValue(header, NAME);
                if (!UNKNOWN.equals(fileName)){
                    InputStream inputStream = getInputStreamBody(inputPart);
                    fileName =  HOME_DIR +"/"+ fileName;
                    byte[] bytes = IOUtils.toByteArray(inputStream);
                    writeFile(bytes, fileName);
                    putFileNameResult(visitDTO, idName, fileName);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return visitDTO.get();
    }

    private void putFileNameResult(AtomicReference<VisitDTO> visitDTO, String idName, String fileName) {
        if (EMPLOYEES.equalsIgnoreCase(idName)){
            visitDTO.get().setFileEmployees(fileName);
        }
        if (STORES.equalsIgnoreCase(idName)){
            visitDTO.get().setFileStores(fileName);
        }
    }

    private InputStream getInputStreamBody(InputPart inputPart) {
        try {
            return ((MultipartInputImpl.PartImpl) inputPart).getBody();
        } catch (IOException e) {
            throw new WebApplicationException("Error processing files.", 404);
        }
    }

    private void writeFile(byte[] content, String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fop = new FileOutputStream(file);
        fop.write(content);
        fop.flush();
        fop.close();
    }

    private String getFileByValue(MultivaluedMap<String, String> headers, String value) {
        String contentDispositionHeader = headers.getFirst("Content-Disposition");

        if (contentDispositionHeader == null) {
            throw new RuntimeException("No Content-Disposition header could be found on the request");
        }

        for (String name : contentDispositionHeader.split(";")) {
            if (name.trim().startsWith(value)) {
                return name.split("=")[1].trim().replaceAll("\"", "");
            }
        }
        return UNKNOWN;
    }
}