package censusanalyser;

import java.util.Iterator;

public class IndiaCensusDAO {

    public int tin;
    public int srNo;
    public String stateCode;
    public String state;
    public int areaInSqKm;
    public int densityPerSqKm;
    public int population;
    public String stateName;

    public IndiaCensusDAO(IndiaCensusCSV censusCSVIterator, IndiaStateCode censusStateIterator) {
        state = censusCSVIterator.state;
        population = censusCSVIterator.population;
        densityPerSqKm = censusCSVIterator.densityPerSqKm;
        areaInSqKm = censusCSVIterator.areaInSqKm;
        srNo=censusStateIterator.SrNo;
        stateCode=censusStateIterator.StateCode;
        tin=censusStateIterator.TIN;


    }


}
