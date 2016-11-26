/*
 * Written by WCUPA Computer Science club members 
 * November 2016
 */
package edu.wcupa.cscclub.projects.martinbrower.reportgenerator.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

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
            
    public CsvReader(String fileName) throws FileNotFoundException
    {
        FileReader reader = new FileReader(fileName);
        _reader = new BufferedReader(reader);
        _pages = new ArrayList<>();
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Getters">
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Setters">
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Helpers">
    
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Publics">
    
    // </editor-fold>    
    
    // </editor-fold>
}
