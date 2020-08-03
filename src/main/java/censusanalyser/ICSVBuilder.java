package censusanalyser;

import com.csvbuilderexception.CSVBuilderException;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface ICSVBuilder {

    <E> Iterator getCSVFileIterator(Reader reader, Class<E> csvClass) throws CSVBuilderException;
    <E> List getCSVFileList(Reader reader, Class<E> csvClass) throws CSVBuilderException;
    <E> String[] getCSVFileMap(Reader reader, Class<E> csvClass) throws CSVBuilderException;



}
