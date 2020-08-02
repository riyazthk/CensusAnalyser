package censusanalyser;

import java.util.Iterator;

public class IndiaCensusDAO {

    public int tin;
    public int SrNo;
    public String stateCode;
    public String state;
    public int areaInSqKm;
    public int densityPerSqKm;
    public int population;

    public IndiaCensusDAO(IndiaCensusCSV censusCSVIterator) {
        state = censusCSVIterator.state;
        population = censusCSVIterator.population;
        densityPerSqKm = censusCSVIterator.densityPerSqKm;
        areaInSqKm = censusCSVIterator.areaInSqKm;

    }

    public IndiaCensusDAO() {

    }

    public IndiaCensusDAO(IndiaStateCode censusCSVIterator) {
        stateCode = censusCSVIterator.StateCode;
        SrNo = censusCSVIterator.SrNo;
        tin = censusCSVIterator.TIN;
        state = censusCSVIterator.StateName;

    }
}
