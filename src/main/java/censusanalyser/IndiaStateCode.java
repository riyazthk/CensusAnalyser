package censusanalyser;

import com.opencsv.bean.CsvBindByName;

import java.util.jar.Attributes;

public class IndiaStateCode {
    @CsvBindByName(column = "SrNo", required = true)
    public int SrNo;

    @CsvBindByName(column = "State Name", required = true)
    public String StateName;


    @CsvBindByName(column = "TIN", required = true)
    public int TIN;

    @CsvBindByName(column = "StateCode", required = true)
    public String StateCode;

    @Override
    public String toString() {
        return "IndiaStateCode{" +
                "SrNo=" + SrNo +
                ", State Name='" + StateName + '\'' +
                ", TIN=" + TIN +
                ", StateCode='" + StateCode + '\'' +
                '}';
    }
}
