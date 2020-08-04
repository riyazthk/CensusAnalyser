package censusanalyser;

public class CensusAnalyserException extends Exception {


    public static  ExceptionType NO_STATE_DATA  ;
    public static ExceptionType NO_CENSUS_DATA  ;

    enum ExceptionType {
        CENSUS_FILE_PROBLEM,UNABLE_TO_PARSE,INCORRECT_DELIMITER_OR_INCORRECTHEADER;
        ;
    }

    ExceptionType type;
    public CensusAnalyserException(String message, String name) {
          super(message);
          this.type=ExceptionType.valueOf(name);
    }

    public CensusAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public CensusAnalyserException(String message, ExceptionType type, Throwable cause) {
        super(message, cause);
        this.type = type;
    }
}
