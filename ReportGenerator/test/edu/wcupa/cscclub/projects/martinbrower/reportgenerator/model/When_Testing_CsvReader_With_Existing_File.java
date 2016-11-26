/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wcupa.cscclub.projects.martinbrower.reportgenerator.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author talk2
 */
public class When_Testing_CsvReader_With_Existing_File
{   
    private final String _fileName;
    
    public When_Testing_CsvReader_With_Existing_File() {
        _fileName = "C:\\Users\\talk2\\Desktop\\Files\\Report1.csv";
    }
    
    @Test
    public void instance_should_be_created()
    {        
        try
        {
            CsvReader reader = new CsvReader(_fileName);            
            assertTrue(true); // Instance created; therefore, test passed!
        }
        catch (FileNotFoundException ex)
        {
            // TODO: Log failure somewhere
            assertTrue(ex.getMessage(), false); // Test failed!
        }
        catch (IOException ex)
        {
            assertTrue(ex.getMessage(), false); // Test failed!
        }
    }
    
}
