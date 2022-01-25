package ca.nait.dmit.domain;

import lombok.Getter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AlbertaCovid19CaseManager {

    private static AlbertaCovid19CaseManager instance;
    private AlbertaCovid19CaseManager() throws IOException {
            albertaCovid19CaseList = loadCsvData();
        }
        public static AlbertaCovid19CaseManager getInstance() throws IOException {
            // https://www.journaldev.com/1377/java-singleton-design-pattern-best-practices-examples#thread-safe-singleton
            if(instance == null){
                synchronized (AlbertaCovid19CaseManager.class) {
                    if(instance == null){
                        instance = new AlbertaCovid19CaseManager();
                    }
                }
            }
            return instance;
        }


    @Getter
    private List<AlbertaCovid19Case> albertaCovid19CaseList;

    private List<AlbertaCovid19Case> loadCsvData() throws IOException {
        List<AlbertaCovid19Case> dataList = new ArrayList<>();

        try (var reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/data/covid-19-alberta-statistics.csv")))) {
            final var delimiter = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
            String line;
            //Skip the first line as it contains column headings
            reader.readLine();
            var dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            //read one line at a time from the input stream
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(delimiter, -1); //the -1 limit allows for any number of fields and not discard trailing empty fields
                //0 - id
                //1 - date reported
                //2 - ahs zone
                //3 - gender
                //4 - age group
                //5 - case status
                //6 - case type
                AlbertaCovid19Case lineData = new AlbertaCovid19Case();
                lineData.setId(Integer.parseInt(values[0].replaceAll("\"", "")));
                lineData.setDateReported(LocalDate.parse(values[1], dateFormatter));
                lineData.setAhsZone(values[2].replaceAll("\"",""));
                lineData.setGender(values[3].replaceAll("\"",""));
                lineData.setAgeGroup(values[4].replaceAll("\"",""));
                lineData.setCaseStatus(values[5].replaceAll("\"",""));
                lineData.setCaseType(values[6].replaceAll("\"",""));
                //add lineData to dataList
                dataList.add(lineData);
            }
        }

        return dataList;
    }
}
