package censusanalyser;

public class CSVBuildFactory {
    public  static ICSVBuilder createCSVBuilder() {
       return new OpenCsvBuilder();
    }
}
