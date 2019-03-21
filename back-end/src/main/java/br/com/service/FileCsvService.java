package br.com.service;

import br.com.model.csv.RowCSV;

import javax.enterprise.context.RequestScoped;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static br.com.domain.HeaderColumEnum.*;

@RequestScoped
public class FileCsvService {

    public List<RowCSV> buildListRowsByFile(String path) {
        Path pathToFile = Paths.get(path);
        return buildListRowsByFile(pathToFile);
    }

    public List<RowCSV> buildListRowsByFile(Path path) {
        List<RowCSV> csv = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                if (!isHeader(attributes)){
                    RowCSV row = new RowCSV(attributes);
                    csv.add(row);
                }
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return csv;
    }

    private boolean isHeader(String[] attributes) {
        return NAME.getValue().contains(attributes[0].toUpperCase().trim())
                && LATITUDE.getValue().contains(attributes[1].toUpperCase().trim())
                && LONGITUDE.getValue().contains(attributes[2].toUpperCase().trim());
    }
}