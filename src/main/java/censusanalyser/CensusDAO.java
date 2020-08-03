package censusanalyser;

public class CensusDAO {

    public int tin;
    public int srNo;
    public String stateCode;
    public String state;
    public int areaInSqKm;
    public int densityPerSqKm;
    public int population;
    public String stateName;

    public CensusDAO(CensusCSV censusCSVIterator) {
        state = censusCSVIterator.state;
        population = censusCSVIterator.population;
        densityPerSqKm = censusCSVIterator.densityPerSqKm;
        areaInSqKm = censusCSVIterator.areaInSqKm;
//        stateCode=censusStateIterator.StateCode;


    }


}
