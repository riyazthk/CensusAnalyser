package censusanalyser;


import com.csvbuilderexception.CSVBuilderException;
import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class CensusAnalyser <E> {
    private String name;
    List<E> censusCSVList=null;
   // List<IndiaStateCode>censusCSVList=null;
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        IndiaCensusCSV indiaCensusCSV=new IndiaCensusCSV();
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            CSVBuildFactory csvBuilder = new CSVBuildFactory();
           // Iterator<IndiaCensusCSV> censusCSVIterator = csvBuilder.getCSVFileIterator(reader, IndiaCensusCSV.class);
           // return this.getCount(censusCSVIterator);
            censusCSVList=csvBuilder.getCSVFileList(reader,IndiaCensusCSV.class);
            return censusCSVList.size();
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
            censusCSVList=csvBuilder.getCSVFileList(reader,IndiaStateCode.class);
            return censusCSVList.size();
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

    public String givenStateWiseSortedCensusData() throws CensusAnalyserException {
        if(censusCSVList==null || censusCSVList.size()==0){
             throw new CensusAnalyserException("No Census Data",CensusAnalyserException.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusCSV>censusComparator=Comparator.comparing(census -> census.state);
        this.sort((Comparator<E>) censusComparator);
        String sortedStateCensusJson=new Gson().toJson(censusCSVList);
        return sortedStateCensusJson;

    }

    private void sort(Comparator<E> censusComparator) {
        for(int firstIndex=0;firstIndex<censusCSVList.size()-1;firstIndex++){
            for(int secondIndex=0;secondIndex<censusCSVList.size()-firstIndex-1;secondIndex++){
                E censusCSV1=censusCSVList.get(secondIndex);
                E censusCSV2=censusCSVList.get(secondIndex+1);
                if(censusComparator.compare(censusCSV1,censusCSV2)>0){
                    censusCSVList.set(secondIndex,censusCSV2);
                    censusCSVList.set(secondIndex+1,censusCSV1);
                }
            }
        }
    }

    public String givenStateCodeWiseSortedCodeData() throws CensusAnalyserException {
        if(censusCSVList==null || censusCSVList.size()==0){
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.NO_CENSUS_DATA);
        }
        Comparator<IndiaStateCode>censusComparator=Comparator.comparing(census -> census.StateCode);
        this.sort((Comparator<E>) censusComparator);
        String sortedStateCodeCensusJson=new Gson().toJson(censusCSVList);
        return sortedStateCodeCensusJson;

    }

    public String givenPopulationWiseSortedCensusData() throws CensusAnalyserException {
        if(censusCSVList==null || censusCSVList.size()==0){
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusCSV>censusComparator=Comparator.comparing(census -> census.population);
        this.sort((Comparator<E>) censusComparator);
        String sortedPopulationCensusJson=new Gson().toJson(censusCSVList);
        return sortedPopulationCensusJson;

    }
}