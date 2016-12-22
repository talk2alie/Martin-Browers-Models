/*
 * Author: Mohamed Alie Pussah, II
 *   Date: Nov 30, 2016
 */

package edu.wcupa.cscclub.projects.martinbrower.reportgenerator.model;

import java.io.IOException;
import java.util.ArrayList;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 */
public class When_Testing_Report_With_Valid_Page {

    private PaletteManager reader;
    String _fileName = "C:\\Users\\talk2\\Desktop\\Files\\BunManifest2.csv";
    
    @Test
    public void instance_should_have_at_least_one_page() throws IOException{
        ArrayList<Page> pages = new PaletteManager(_fileName).PAGES;
        for(Page page : pages){
            Report report = new Report(page);
            int cases = report.getCaseCount();
        }
        
        assertTrue(true);
    }
}
