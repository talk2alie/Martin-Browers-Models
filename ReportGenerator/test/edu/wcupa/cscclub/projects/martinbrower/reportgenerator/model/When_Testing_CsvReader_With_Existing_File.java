/*
 * Tests for the CsvReader in good situations
 */
package edu.wcupa.cscclub.projects.martinbrower.reportgenerator.model;

import java.io.File;
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
    private final boolean _expectCartTotal;

    public When_Testing_CsvReader_With_Existing_File() {
        _fileName = "C:\\Users\\talk2\\Desktop\\Files\\BunManifest2.csv";
        _expectedPageCount = 90;
//        _fileName = "C:\\Users\\talk2\\Desktop\\Files\\Report1.csv";
//        _expectedPageCount = 295;
        _expectCartTotal = true;
    }

    @Test
    public void instance_should_be_created() {
        try {
            CsvReader csvReader = new CsvReader(_fileName);
            assertTrue(true); // Instance created; therefore, test passed!
        }
        catch (FileNotFoundException ex) {
            // TODO: Log the exception
            assertTrue(ex.getMessage(), false); // Test failed! :-(
        }
        catch (IOException ex) {
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

    @Test
    public void instance_pages_should_each_have_at_least_one_cell_in_their_columns() {
        try {

            CsvReader reader = new CsvReader(_fileName);
            boolean eachColumnHasAtLeastOnceCell = false;
            for (Page page : reader.PAGES) {
                for (Column column : page.getColumns()) {
                    eachColumnHasAtLeastOnceCell = column.CELLS.size() > 0;
                }
            }
            assertTrue(eachColumnHasAtLeastOnceCell);
        }
        catch (IOException ex) {
            //TODO: Log the exception
            assertTrue(ex.getMessage(), false); // Test failed! :-(
        }
    }

    @Test
    public void instance_pages_should_each_have_cart_total_above_zero() {
        try {

            CsvReader reader = new CsvReader(_fileName);
            boolean eachPageHasACartTotalAboveZero = false;
            for (Page page : reader.PAGES) {
                eachPageHasACartTotalAboveZero = page.getCartTotal() > 0;
            }
            assertTrue(eachPageHasACartTotalAboveZero);
        }
        catch (IOException ex) {
            //TODO: Log the exception
            assertTrue(ex.getMessage(), false); // Test failed! :-(
        }
    }

    @Test
    public void instance_should_output_parsed_data_to_given_file() {
        try {
            String fileName = "C:\\Users\\talk2\\Desktop\\Files\\TestOutput.csv";
            CsvReader reader = new CsvReader(_fileName);
            reader.saveAsCSV(fileName);
            File file = new File(fileName);
            assertTrue(file.exists());
        }
        catch (IOException ex) {
            //TODO: Log the exception
            assertTrue(ex.getMessage(), false); // Test failed! :-(
        }
    }
}
