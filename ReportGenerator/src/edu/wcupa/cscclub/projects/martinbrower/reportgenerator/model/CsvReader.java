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
    private Map<String, String> headers;
    
    private final String SUMMARY_HEADER_PATTERN = 
            "\\b(ROUTE|WRIN|TRAILER\\s*(POSITION|POS)|STOP|CASES|DESCRIPTION)\\s*:{1}\\s*\\,+\\w+\\b";
    
    // </editor-fold>
       
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
            
    public CsvReader(String fileName) throws FileNotFoundException, IOException
    {
        FileReader reader = new FileReader(fileName);
        _reader = new BufferedReader(reader);
        _pages = new ArrayList<>();
        headers = new HashMap<>();
        String line;
        while((line = _reader.readLine()) != null) {
            Map<String, String> currentHeaders = getSummaryHeader(line);
            if(currentHeaders.size() > 0) {
                headers.putAll(currentHeaders);
            }
        }
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Getters">
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Setters">
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Helpers">
    
    private Map<String, String> getSummaryHeader(String line) {
        
        Pattern pattern = Pattern.compile(SUMMARY_HEADER_PATTERN);
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
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Publics">
    
    // </editor-fold>    
    
    // </editor-fold>
}
