package censusanalyser;


import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;


public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_TYPE = "./src/main/resources/IndiaStateCensusData.txt";
    private static final String CHECK_DELIMITER = "./src/test/resources/object-list-sample.csv";
    private static final String INDIA_STATECODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
    private static final String STATECODE_WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCode.csv";
    private static final String STATECODE_WRONG_CSV_FILE_TYPE = "./src/test/resources/IndiaStateCode.txt";
    private static final String US_CENSUS_FILE_PATH = "./src/test/resources/USCensusData.csv";

    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() throws IOException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(CensusCSV.class, INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndianStateCSVFileReturnsCorrectRecords() throws IOException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.IndiaStateCodeData(INDIA_STATECODE_CSV_FILE_PATH);
            Assert.assertEquals(37, numOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongPath_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(CensusCSV.class, WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaStateCode_WithWrongPath_ShouldThrowException() throws IOException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaStateCodeData(STATECODE_WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateCode_WithWrongType_ShouldThrowException() throws IOException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(CensusCSV.class, WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianStateCode_WithWrongDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadObjectsListSample(CHECK_DELIMITER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }

    }

    @Test
    public void givenIndiaStateCode_ToWrongHeader_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadObjectsListSampleheader(CHECK_DELIMITER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }

    }


    @Test
    public void givenIndianCodeData_WhenSortedStateCode_ShouldReturnSortedResult() throws IOException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(CensusCSV.class, INDIA_CENSUS_CSV_FILE_PATH);
            censusAnalyser.loadIndiaStateCodeData(INDIA_STATECODE_CSV_FILE_PATH);
            String sortedStateCodeData = censusAnalyser.givenStateCodeWiseSortedCodeData();
            CensusDAO[] censusCSV = new Gson().fromJson(sortedStateCodeData, CensusDAO[].class);
            Assert.assertEquals("WB", censusCSV[0].stateCode);

        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);

        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedPopulation_ShouldReturnSortedResult() throws IOException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(CensusCSV.class, INDIA_CENSUS_CSV_FILE_PATH);
            censusAnalyser.loadIndiaStateCodeData(INDIA_STATECODE_CSV_FILE_PATH);
            String sortedPopulationData = censusAnalyser.givenPopulationWiseSortedCensusData();
            CensusCSV[] censusCSV = new Gson().fromJson(sortedPopulationData, CensusCSV[].class);
            Assert.assertNotEquals(29, censusCSV[0].population);

        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);

        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedDensity_ShouldReturnSortedResult() throws CensusAnalyserException, IOException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(CensusCSV.class, INDIA_CENSUS_CSV_FILE_PATH);
            censusAnalyser.loadIndiaStateCodeData(INDIA_STATECODE_CSV_FILE_PATH);
            String sortedDensityData = censusAnalyser.givenDensityWiseSortedCensusData();
            CensusDAO[] censusCSV = new Gson().fromJson(sortedDensityData, CensusDAO[].class);
            Assert.assertEquals(1102.0, censusCSV[0].populationDensity, 0.001);

        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);

        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedArea_ShouldReturnSortedResult() throws CensusAnalyserException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(CensusCSV.class, INDIA_CENSUS_CSV_FILE_PATH);
            censusAnalyser.loadIndiaStateCodeData(INDIA_STATECODE_CSV_FILE_PATH);
            String sortedAreaData = censusAnalyser.givenAreaWiseSortedCensusData();
            CensusDAO[] censusDAO = new Gson().fromJson(sortedAreaData, CensusDAO[].class);
            Assert.assertEquals(342239.0, censusDAO[0].totalArea, 0.001);

        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUSCensusCSVFileReturnsCorrectRecords() throws IOException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadUsCensusData(UsCensusCSV.class,US_CENSUS_FILE_PATH);
            Assert.assertEquals(51, numOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }
    @Test
    public void givenUsCensusData_WhenSortedPopulation_ShouldReturnSortedResult() throws IOException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadUsCensusData(UsCensusCSV.class, US_CENSUS_FILE_PATH);
            String sortedPopulationData = censusAnalyser.givenPopulationWiseSortedUsCensusData();
            CensusDAO[] censusDAO = new Gson().fromJson(sortedPopulationData, CensusDAO[].class);
            Assert.assertEquals("California", censusDAO[0].state);

        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);

        }
    }
    @Test
    public void givenUsCensusData_WhenSortedPopulationDensity_ShouldReturnSortedResult() throws IOException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadUsCensusData(UsCensusCSV.class, US_CENSUS_FILE_PATH);
            String sortedPopulationData = censusAnalyser.givenPopulationDensityWiseSortedUsCensusData();
            CensusDAO[] censusDAO = new Gson().fromJson(sortedPopulationData, CensusDAO[].class);
            Assert.assertEquals(3805.61, censusDAO[0].populationDensity,0.001);

        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);

        }
    }
    @Test
    public void givenUsCensusData_WhenSortedArea_ShouldReturnSortedResult() throws IOException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadUsCensusData(UsCensusCSV.class, US_CENSUS_FILE_PATH);
            String sortedPopulationData = censusAnalyser.givenUsAreaWiseSortedUsCensusData();
            CensusDAO[] censusDAO = new Gson().fromJson(sortedPopulationData, CensusDAO[].class);
            Assert.assertEquals(1723338.01, censusDAO[0].totalArea,0.001);

        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);

        }
    }

}
