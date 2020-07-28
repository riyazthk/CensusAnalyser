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

public class CensusAnalyser {
    private String name;

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            CsvToBeanBuilder<IndiaCensusCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(IndiaCensusCSV.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<IndiaCensusCSV> csvToBean = csvToBeanBuilder.build();
            Iterator<IndiaCensusCSV> censusCSVIterator = csvToBean.iterator();
            int namOfEnteries = 0;
            while (censusCSVIterator.hasNext()) {
                namOfEnteries++;
                IndiaCensusCSV censusData = censusCSVIterator.next();
            }
            return namOfEnteries;
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
            CsvToBeanBuilder<IndiaStateCode> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(IndiaStateCode.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<IndiaStateCode> csvToBean = csvToBeanBuilder.build();
            Iterator<IndiaStateCode> stateCodeIterator = csvToBean.iterator();
            int namOfEnteries = 0;
            while (stateCodeIterator.hasNext()) {
                namOfEnteries++;
                IndiaStateCode stateCodeCSVData = stateCodeIterator.next();
            }
            return namOfEnteries;
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
}

