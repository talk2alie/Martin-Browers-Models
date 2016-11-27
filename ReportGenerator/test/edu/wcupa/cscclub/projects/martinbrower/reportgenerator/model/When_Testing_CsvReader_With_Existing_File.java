/*
 * Tests for the CsvReader in good situations
 */
package edu.wcupa.cscclub.projects.martinbrower.reportgenerator.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Mohamed Alie Pussah (mp754927@wcupa.edu)
 */
public class When_Testing_CsvReader_With_Existing_File
{   
    private final String _fileName;
    // This value should reflect the number of pages in the CSV file
    // We also have to make this value dynamic based on the CSV file
    private final int _expectedPageCount;
    
    public When_Testing_CsvReader_With_Existing_File() {
        _fileName = "C:\\Users\\talk2\\Desktop\\Files\\Report1.csv";
        _expectedPageCount = 295;
    }
    
    @Test
    public void instance_should_be_created()
    {        
        try
        {
            CsvReader _reader = new CsvReader(_fileName);            
            assertTrue(true); // Instance created; therefore, test passed!
        }
        catch (FileNotFoundException ex)
        {
            // TODO: Log the exception
            assertTrue(ex.getMessage(), false); // Test failed! :-(
        }
        catch (IOException ex)
        {
            assertTrue(ex.getMessage(), false); // Test failed! :-(
        }
    }
    
    @Test
    public void instance_page_count_should_equal_page_count_in_file() {
        try {
            
            CsvReader reader = new CsvReader(_fileName);
            assertEquals(_expectedPageCount, reader.PAGES.size());
        }
        catch (IOException ex) {
            //TODO: Log the exception
            assertTrue(ex.getMessage(), false); // Test failed! :-(
        }        
    }    
}
