package censusanalyser;


import com.csvbuilderexception.CSVBuilderException;
import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.StreamSupport;

public class CensusAnalyser<E> {
    private String name;
    HashMap<String,IndiaCensusDAO> censusList = null;

    public CensusAnalyser() {
        this.censusList = new HashMap<String, IndiaCensusDAO>();
    }
    // List<IndiaStateCode>censusCSVList=null;
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        IndiaCensusCSV indiaCensusCSV = new IndiaCensusCSV();
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            CSVBuildFactory csvBuilder = new CSVBuildFactory();
            Iterator<IndiaCensusCSV> censusCSVIterator = csvBuilder.getCSVFileIterator(reader, IndiaCensusCSV.class);
            while (censusCSVIterator.hasNext()){
                IndiaCensusDAO indiaCensusDAO = new IndiaCensusDAO(censusCSVIterator.next());
                this.censusList.put(indiaCensusDAO.state,indiaCensusDAO);
            }
            // return this.getCount(censusCSVIterator);
            //List<E> censusCSVList = csvBuilder.getCSVFileList(reader, IndiaCensusCSV.class);
            return this.censusList.size();
        } catch (IOException | CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.
                            CENSUS_FILE_PROBLEM);
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

    public int loadIndiaStateCodeData(String FilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(FilePath));) {
            CSVBuildFactory csvBuilder = new CSVBuildFactory();
            Iterator<IndiaStateCode> censusCSVIterator = csvBuilder.getCSVFileIterator(reader, IndiaStateCode.class);
            while (censusCSVIterator.hasNext()) {
                IndiaCensusDAO indiaCensusDAO = new IndiaCensusDAO(censusCSVIterator.next());
                this.censusList.put(indiaCensusDAO.state,indiaCensusDAO);
            }
            return this.censusList.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.
                            CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
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

//    public String givenStateWiseSortedCensusData() throws CensusAnalyserException {
//        if (censusList == null || censusList.size() == 0) {
//            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.NO_CENSUS_DATA);
//        }
//        Comparator<IndiaCensusDAO> censusComparator = Comparator.comparing(census -> census.state);
//        this.sort(censusComparator);
//        String sortedStateCensusJson = new Gson().toJson(this.censusList);
//        return sortedStateCensusJson;
//
//    }

//    private void sort(Comparator<IndiaCensusDAO> censusComparator) {
//        for (int firstIndex = 0; firstIndex < censusList.size() - 1; firstIndex++) {
//            for (int secondIndex = 0; secondIndex < censusList.size() - firstIndex - 1; secondIndex++) {
//                IndiaCensusDAO censusCSV1 = censusList.get(secondIndex);
//                IndiaCensusDAO censusCSV2 = censusList.get(secondIndex + 1);
//                if (censusComparator.compare(censusCSV1, censusCSV2) > 0) {
//                    censusList.set(secondIndex, censusCSV2);
//                    censusList.set(secondIndex + 1, censusCSV1);
//                }
//            }
//        }
//    }

//    public String givenStateCodeWiseSortedCodeData() throws CensusAnalyserException {
//        if (censusList == null || censusList.size() == 0) {
//            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.NO_CENSUS_DATA);
//        }
//        Comparator<IndiaStateCode> censusComparator = Comparator.comparing(census -> census.StateCode);
//        this.sort((Comparator<E>) censusComparator);
//        String sortedStateCodeCensusJson = new Gson().toJson(censusList);
//        return sortedStateCodeCensusJson;
//
//    }
//
//    public String givenPopulationWiseSortedCensusData() throws CensusAnalyserException {
//        if (censusList == null || censusList.size() == 0) {
//            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.NO_CENSUS_DATA);
//        }
//        Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.population);
//        this.sort((Comparator<E>) censusComparator);
//        String sortedPopulationCensusJson = new Gson().toJson(censusList);
//        return sortedPopulationCensusJson;
//
//    }
//
//    public String givenDensityWiseSortedCensusData() throws CensusAnalyserException {
//        if (censusList == null || censusList.size() == 0) {
//            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.NO_CENSUS_DATA);
//        }
//        Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.densityPerSqKm);
//        this.sort((Comparator<E>) censusComparator);
//        String sortedDensityCensusJson = new Gson().toJson(censusList);
//        return sortedDensityCensusJson;
//
//    }
//
//    public String givenAreaWiseSortedCensusData() throws CensusAnalyserException, IOException {
//
//        if (censusList == null || censusList.size() == 0) {
//            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.NO_CENSUS_DATA);
//        }
//        Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.areaInSqKm);
//        this.sort((Comparator<E>) censusComparator);
//        String sortedAreaCensusJson = new Gson().toJson(censusList);
//        FileWriter fileWriter = new FileWriter("SortedArea.json");
//        fileWriter.write(sortedAreaCensusJson);
//        fileWriter.close();
//        return sortedAreaCensusJson;
//
//    }

}

