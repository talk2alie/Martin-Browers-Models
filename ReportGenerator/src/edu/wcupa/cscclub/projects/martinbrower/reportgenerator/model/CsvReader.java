/*
 * Written by WCUPA Computer Science club members 
 * November 2016
 */
package edu.wcupa.cscclub.projects.martinbrower.reportgenerator.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author talk2
 */
public class CsvReader
{
    
    // <editor-fold defaultstate="collapsed" desc="Fields">
    
    private ArrayList<Page> _pages;
    private int _pageCount;
    private BufferedReader _reader;  
    
    
    // </editor-fold>
       
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
            
    public CsvReader(String fileName) throws FileNotFoundException, IOException
    {
        FileReader reader = new FileReader(fileName);
        _reader = new BufferedReader(reader);
        _pages = new ArrayList<>();
        processFile(_reader);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Getters">
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Setters">
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Helpers">
    
    private Map<String, String> getSummaryHeader(String line) {
        String summaryHeaderPattern = 
            "\\b(ROUTE|WRIN|TRAILER\\s*(POSITION|POS)|STOP|CASES|DESCRIPTION)\\s*:{1}\\s*\\,+\\w+\\b";
        Pattern pattern = Pattern.compile(summaryHeaderPattern);
        Matcher matcher = pattern.matcher(line);
        
        Map<String, String> summaryHeaders = new HashMap<>();
        while(matcher.find()) {
            String match = matcher.group();
            String header = match.substring(0, match.indexOf(":")).trim();
            String value = match.substring(match.lastIndexOf(",") + 1);
            summaryHeaders.put(header, value);            
        }     
        return summaryHeaders;
    }
    
    private String getPageNumber(String line)
    {
        String pagePattern = "^\\bPage\\s*\\d+\\b";
        Pattern pattern = Pattern.compile(pagePattern);
        Matcher matcher = pattern.matcher(line);
        
        if(matcher.find()) {
            String match = matcher.group();
            String pageNumber = match.substring(match.indexOf(" ") + 1);
            return pageNumber;                    
        }        
        return null;
    }
    
    private Map<String, Integer> getColumnHeaders(String line)
    {
        Map<String, Integer> columnHeaders = new HashMap<>();
        
        String columnHeaderPattern = "\\b(ROUTE|WRIN|TRAILER\\s*(POSITION|POS)|STOP|CASES|DESCRIPTION)\\b(?=\\s*\\,+)";
        Pattern pattern = Pattern.compile(columnHeaderPattern);
        Matcher matcher = pattern.matcher(line);
        
        while(matcher.find()){
            String header = matcher.group();
            int index = line.indexOf(header);
            columnHeaders.put(header, index);
        }
        
        return columnHeaders;
    }
    
    private Map<Integer, String> getData(String line)
    {
        return new HashMap<>();
    }
    
    private void updatePageColumns(Page page, Map<Integer, String> row)
    {
        return;
    }
        
    private void processFile(BufferedReader reader) throws IOException {
        Page currentPage = null;
        
        String line;
        while((line = _reader.readLine()) != null) {
            if(currentPage == null) currentPage = new Page();
            
            String pageNumber = getPageNumber(line);
            if(pageNumber != null && pageNumber.length() > 0) {
                currentPage.setNumber(Integer.parseInt(pageNumber.trim()));
                _pages.add(currentPage);
                currentPage = null;
                continue;
            }
            
            Map<String, String> currentSummaryHeaders = getSummaryHeader(line);
            if(currentSummaryHeaders.size() > 0) {
                currentPage.getSummaryHeaders().putAll(currentSummaryHeaders);
                continue;
            }
            
            Map<String, Integer> currentColumnHeaders = getColumnHeaders(line);
            if(currentColumnHeaders.size() > 0) {
                currentPage.getColumnHeaders().putAll(currentColumnHeaders);
                continue;
            }
            
            Map<Integer, String> dataRow = getData(line);
            if(dataRow.size() > 0) {
                updatePageColumns(currentPage, dataRow);
            }
        }
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Publics">
    
    // </editor-fold>    
    
    // </editor-fold>

    
}
