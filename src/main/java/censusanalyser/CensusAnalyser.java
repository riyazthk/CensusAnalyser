package censusanalyser;


import com.csvbuilderexception.CSVBuilderException;
import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.Map.Entry.comparingByKey;
import static java.util.stream.Collectors.toMap;

public class CensusAnalyser<E> implements Cloneable {
    private String name;
    HashMap<String, CensusDAO> censusMap = null;
    public int count = 0;

    public CensusAnalyser() {
        this.censusMap = new HashMap<String, CensusDAO>();
    }


    public <E> int loadIndiaCensusData(Class csvClass, String... csvFilePath) throws CensusAnalyserException, IOException {

        CSVBuildFactory csvBuilder = new CSVBuildFactory();
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath[0]));) {
            Iterator<E> censusIterator = csvBuilder.getCSVFileIterator(reader, csvClass);
            Iterable<E> csvIterable = () -> censusIterator;
            if (csvClass.getName().equals("censusanalyser.CensusCSV")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(CensusCSV.class::cast)
                        .forEach(censusCSV -> censusMap.put(censusCSV.state, new CensusDAO(censusCSV)));
                return censusMap.size();
            }
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.
                            CENSUS_FILE_PROBLEM);
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.
                            CENSUS_FILE_PROBLEM);
        }
        return censusMap.size();
    }

    public int loadIndiaStateCodeData(String FilePath) throws IOException, CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(FilePath));) {
            CSVBuildFactory csvBuilder = new CSVBuildFactory();
            Iterator<IndiaStateCode> stateCSVIterator = csvBuilder.getCSVFileIterator(reader, IndiaStateCode.class);
            Iterable<IndiaStateCode> csvIterable = () -> stateCSVIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .filter(stateCSV -> censusMap.get(stateCSV.state) != null)
                    .forEach(stateCSV -> censusMap.get(stateCSV.state).stateCode = stateCSV.StateCode);
            return censusMap.size();

        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.
                            CENSUS_FILE_PROBLEM);
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.
                            CENSUS_FILE_PROBLEM);
        }
    }

    public int loadUsCensusData(String usCensusCSVFilePath) throws CensusAnalyserException {
        CSVBuildFactory csvBuilder = new CSVBuildFactory();
        try {
            try (Reader reader = Files.newBufferedReader(Paths.get(usCensusCSVFilePath));) {
                Iterator<UsCensusCSV> censusIterator = csvBuilder.getCSVFileIterator(reader, UsCensusCSV.class);
                Iterable<UsCensusCSV> csvIterable = () -> censusIterator;
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(UsCensusCSV.class::cast)
                        .forEach(usCensusCSV -> censusMap.put(usCensusCSV.state, new CensusDAO(usCensusCSV)));
                return censusMap.size();
            }

        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.
                            CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.
                            CENSUS_FILE_PROBLEM);
        }
    }

    public int IndiaStateCodeData(String FilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(FilePath));) {
            CSVBuildFactory csvBuilder = new CSVBuildFactory();
            Iterator<IndiaStateCode> stateCodeIterator = csvBuilder.getCSVFileIterator(reader, IndiaStateCode.class);
            return this.getCount(stateCodeIterator);
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.
                            CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

    public void loadObjectListSample(String checkDelimiter) throws CensusAnalyserException {
        String line = "";
        String Delimiter = ";";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(checkDelimiter))) {
            while ((line = bufferedReader.readLine()) != null) {
                String data = line.substring(line.indexOf(Delimiter), line.lastIndexOf(Delimiter));
            }


        } catch (Exception e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.
                            CENSUS_FILE_PROBLEM);
        }
    }

    public void loadObjectListSampleheader(String checkDelimiter) throws CensusAnalyserException {
        String line = "";
        int i = 0;
        ObjectListSample objectListSample = new ObjectListSample();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(checkDelimiter))) {
            while ((line = bufferedReader.readLine()) != null) {
                String data[] = line.split(",");
                objectListSample.email.equals(data[i]);
                i++;
            }


        } catch (Exception e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.
                            CENSUS_FILE_PROBLEM);
        }
    }

    public void loadObjectsListSample(String checkDelimiter) throws CensusAnalyserException {
        String line = "";
        String Delimiter = ";";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(checkDelimiter))) {
            while ((line = bufferedReader.readLine()) != null) {
                String data = line.substring(line.indexOf(Delimiter), line.lastIndexOf(Delimiter));
            }


        } catch (Exception e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.
                            CENSUS_FILE_PROBLEM);
        }
    }

    public void loadObjectsListSampleheader(String checkDelimiter) throws CensusAnalyserException {
        String line = "";
        int i = 0;
        ObjectListSample objectListSample = new ObjectListSample();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(checkDelimiter))) {
            while ((line = bufferedReader.readLine()) != null) {
                String data[] = line.split(",");
                objectListSample.email.equals(data[i]);
                i++;
            }


        } catch (Exception e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.
                            CENSUS_FILE_PROBLEM);
        }
    }

    private <E> int getCount(Iterator<E> iterator) {
        Iterable<E> csvIterable = () -> iterator;
        int numOfEnteries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
        return numOfEnteries;
    }

    private void sort(List<CensusDAO> censusDAO, Comparator<CensusDAO> censusComparator) {
        for (int firstIndex = 0; firstIndex < censusDAO.size() - 1; firstIndex++) {
            for (int secondIndex = 0; secondIndex < censusDAO.size() - firstIndex - 1; secondIndex++) {
                CensusDAO censusCSV1 = censusDAO.get(secondIndex);
                CensusDAO censusCSV2 = censusDAO.get(secondIndex + 1);
                if (censusComparator.compare(censusCSV1, censusCSV2) > 0) {
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
        String sortedStateCensusJson = new Gson().toJson(this.censusMap);
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