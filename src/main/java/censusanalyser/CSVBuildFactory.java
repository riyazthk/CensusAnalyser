package censusanalyser;

import com.censusanalyser.OpenCsvBuilder;
import com.csvbuilderexception.CSVBuilderException;

import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class CSVBuildFactory implements ICSVBuilder {
    public Iterator getCSVFileIterator(Reader reader, Class csvClass) throws CSVBuilderException {

        return new OpenCsvBuilder().getCSVFileIterator(reader, csvClass);

    }

    @Override
    public List getCSVFileList(Reader reader, Class csvClass) throws CSVBuilderException {
        return new OpenCsvBuilder().getCSVFileList(reader, csvClass);
    }

    @Override
    public String[] getCSVFileMap(Reader reader, Class csvClass) throws CSVBuilderException {
        String[] a= new OpenCsvBuilder().getCSVFileMap(reader, csvClass);
        return a;

    }
}
