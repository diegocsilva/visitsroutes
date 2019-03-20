package br.com.service;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.MultivaluedMap;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class FileUploadService {

    public List<String> createFile(MultipartInput input) {
        List<String> names = new ArrayList<>();

        input.getParts().forEach(inputPart -> {
            try {
                MultivaluedMap<String, String> header = inputPart.getHeaders();
                String fileName = this.getFilename(header);
                InputStream inputStream = inputPart.getBody(InputStream.class, null);
                byte[] bytes = IOUtils.toByteArray(inputStream);
                fileName = "/home/diego/" + fileName;
                writeFile(bytes, fileName);
                names.add(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return names;
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

    private String getFilename(MultivaluedMap<String, String> headers) {
        String contentDispositionHeader = headers.getFirst("Content-Disposition");

        if (contentDispositionHeader == null) {
            throw new RuntimeException("No Content-Disposition header could be found on the request");
        }

        for (String name : contentDispositionHeader.split(";")) {
            if (name.trim().startsWith("filename")) {
                return name.split("=")[1].trim().replaceAll("\"", "");
            }
        }

        return "unknown";
    }
}