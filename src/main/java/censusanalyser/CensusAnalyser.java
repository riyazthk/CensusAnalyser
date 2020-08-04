package censusanalyser;


import com.google.gson.Gson;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class CensusAnalyser<E> {
    HashMap<String, CensusDAO> censusMap = new HashMap<>();

    public enum Country {INDIA, US}

    ;
    CensusLoader censusLoader = new CensusLoader();

    public int loadCensusData(Country country, String... csvFilePath) throws CensusAnalyserException, RuntimeException {
        censusMap = (HashMap<String, CensusDAO>) censusLoader.loadCensusData(country, csvFilePath);
        return censusMap.size();
    }


    private void sort(List<CensusDAO> censusDAO, Comparator<CensusDAO> censusComparator) {
        for (int firstIndex = 0; firstIndex < censusDAO.size() - 1; firstIndex++) {
            for (int secondIndex = 0; secondIndex < censusDAO.size() - firstIndex - 1; secondIndex++) {
                CensusDAO censusCSV1 = censusDAO.get(secondIndex);
                CensusDAO censusCSV2 = censusDAO.get(secondIndex + 1);
                if (censusComparator.compare(censusCSV1, censusCSV2) < 0) {
                    censusDAO.set(secondIndex, censusCSV2);
                    censusDAO.set(secondIndex + 1, censusCSV1);
                }
            }
        }
    }

    public String givenStateWiseSortedCensusData() throws CensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.state);
        List<CensusDAO> censusDAOS = censusMap.values().stream().collect(Collectors.toList());
        this.sort(censusDAOS, censusComparator);
        String sortedStateCensusJson = new Gson().toJson(censusDAOS);
        return sortedStateCensusJson;

    }


    public String givenPopulationWiseSortedCensusData() throws CensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.population);
        List<CensusDAO> censusDAOS = censusMap.values().stream().collect(Collectors.toList());
        this.sort(censusDAOS, censusComparator);
        String sortedPopulationCensusJson = new Gson().toJson(censusDAOS);
        return sortedPopulationCensusJson;

    }


    public String givenStateCodeWiseSortedCodeData() throws CensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.stateCode);
        List<CensusDAO> censusDAOS = censusMap.values().stream().collect(Collectors.toList());
        this.sort(censusDAOS, censusComparator);
        String sortedStateCodeCensusJson = new Gson().toJson(censusDAOS);
        return sortedStateCodeCensusJson;

    }


    public String givenDensityWiseSortedCensusData() throws CensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.populationDensity);
        List<CensusDAO> censusDAOS = censusMap.values().stream().collect(Collectors.toList());
        this.sort(censusDAOS, censusComparator);
        String sortedDensityCensusJson = new Gson().toJson(censusDAOS);
        return sortedDensityCensusJson;

    }

    public String givenAreaWiseSortedCensusData() throws CensusAnalyserException, IOException {

        if (censusMap == null || censusMap.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.totalArea);
        List<CensusDAO> censusDAOS = censusMap.values().stream().collect(Collectors.toList());
        this.sort(censusDAOS, censusComparator);
        String sortedAreaCensusJson = new Gson().toJson(censusDAOS);
        FileWriter fileWriter = new FileWriter("SortedArea.json");
        fileWriter.write(sortedAreaCensusJson);
        fileWriter.close();
        return sortedAreaCensusJson;

    }


}