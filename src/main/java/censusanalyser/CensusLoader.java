package censusanalyser;

import com.csvbuilderexception.CSVBuilderException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class CensusLoader<E> {
    HashMap<String, CensusDAO> censusMap = new HashMap<>();

    public Map loadCensusData(CensusAnalyser.Country country, String... csvFilePath) throws CensusAnalyserException {
        if (country.equals(CensusAnalyser.Country.INDIA))
            return this.loadCensusData(IndianCensusCSV.class, csvFilePath);
        else if (country.equals(CensusAnalyser.Country.US))
            return this.loadCensusData(UsCensusCSV.class, csvFilePath);
        else
            throw new CensusAnalyserException("Invalid Country", CensusAnalyserException.ExceptionType.INVALIDCOUNTRY);
    }

    private <E> Map loadCensusData(Class<E> censusCSVClass, String... csvFilePath) throws CensusAnalyserException, RuntimeException {
        CSVBuildFactory csvBuilder = new CSVBuildFactory();
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath[0]));) {
            Iterator<E> censusIterator = csvBuilder.getCSVFileIterator(reader, censusCSVClass);
            Iterable<E> csvIterable = () -> censusIterator;
            if (censusCSVClass.getName().equals("censusanalyser.IndianCensusCSV")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IndianCensusCSV.class::cast)
                        .forEach(censusCSV -> censusMap.put(censusCSV.state, new CensusDAO(censusCSV)));
            } else if (censusCSVClass.getName().equals("censusanalyser.UsCensusCSV")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(UsCensusCSV.class::cast)
                        .forEach(censusCSV -> censusMap.put(censusCSV.state, new CensusDAO(censusCSV)));

            }
            if (csvFilePath.length == 1) return censusMap;
            this.loadIndiaStateCodeData(csvFilePath[1]);

        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }


        return censusMap;
    }

    public int loadIndiaStateCodeData(String stateCensusFilePath) throws IOException, CensusAnalyserException {
        CSVBuildFactory csvBuilder = new CSVBuildFactory();
        try (Reader reader = Files.newBufferedReader(Paths.get(stateCensusFilePath));) {
            Iterator<E> censusIterator = csvBuilder.getCSVFileIterator(reader, IndiaStateCode.class);
            Iterable<E> csvIterable = () -> censusIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .map(IndiaStateCode.class::cast)
                    .filter(stateCSV -> censusMap.get(stateCSV.state) != null)
                    .forEach(stateCSV -> censusMap.get(stateCSV.state).stateCode = stateCSV.StateCode);
        }
        return censusMap.size();

    }

}


