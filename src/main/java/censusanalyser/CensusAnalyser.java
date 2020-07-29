package censusanalyser;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;


public class CensusAnalyser {
    private String name;

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            Iterator<IndiaCensusCSV> censusCSVIterator = new openCsvBuilder().getCSVFileIterator(reader, IndiaCensusCSV.class);
            return this.getCount(censusCSVIterator);
        } catch (IOException e) {
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
            Iterator<IndiaStateCode> stateCodeIterator = new openCsvBuilder().getCSVFileIterator(reader, IndiaStateCode.class);
            return this.getCount(stateCodeIterator);
        } catch (IOException e) {
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


}