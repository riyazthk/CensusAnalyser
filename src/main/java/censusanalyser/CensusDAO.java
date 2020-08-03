package censusanalyser;

public class CensusDAO {

    public String stateCode;
    public String state;
    public double totalArea;
    public double populationDensity;
    public int population;

    public CensusDAO(CensusCSV censusCSVIterator) {
        state = censusCSVIterator.state;
        population = censusCSVIterator.population;
        populationDensity = censusCSVIterator.densityPerSqKm;
        totalArea = censusCSVIterator.areaInSqKm;


    }


    public CensusDAO(UsCensusCSV usCensusCSV) {
        state=usCensusCSV.state;
        stateCode=usCensusCSV.stateId;
        population=usCensusCSV.population;
        populationDensity=usCensusCSV.populationDensity;
        totalArea=usCensusCSV.totalArea;
    }
}
