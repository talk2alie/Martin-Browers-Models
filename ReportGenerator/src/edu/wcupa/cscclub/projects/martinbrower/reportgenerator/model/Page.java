/*
 * Written by WCUPA Computer Science club members 
 * November 2016
 */
package edu.wcupa.cscclub.projects.martinbrower.reportgenerator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Mohamed Alie Pussah (mp754927@wcupa.edu)
 * Holds information about a single page; either 
 * from a parsed CSV file, or from a generated 
 * report
 */
public class Page
{
    private int _number;
    private int _cartTotal;
    private Map<String, String> _summaryHeaders;
    private Map<String, Integer> _columnHeaders;
    private Map<String, ArrayList<String>> _columns;
    
    public Page() {
        _summaryHeaders = new HashMap<>();
        _columnHeaders = new HashMap<>();
        _columns = new HashMap<>();
    }

    /**
     * Gets the cart total, or the sum of the CASES column as read from the CSV 
     * file or as calculated from the generated report. This value can be used 
     * to determine whether or not a report generated from this page instance 
     * will have one or more sections. If this value is higher than 21, the 
     * generated report from this page will have more that one section and the 
     * value of this field in those sections are guaranteed to be 21 or less
     * @return the _cartTotal
     */
    public int getCartTotal()
    {
        return _cartTotal;
    }

    /**
     * Sets the cart total or the sum of the CASES field as read from the CSV 
     * file or as calculated from the generated report 
     * @param cartTotal the cartTotal to set
     */
    public void setCartTotal(int cartTotal)
    {
        this._cartTotal = cartTotal;
    }

    /**
     * Gets all required column headers from the CSV file
     * @return the column headers found in the CSV file, and based on the 
     * predefined values requested by Martin Browers
     */
    public Map<String, Integer> getColumnHeaders()
    {
        return _columnHeaders;
    }

    /**
     * Gets all required columns, both header and data, as defined by Martin 
     * Browers
     * @return the columns from the CSV file
     */
    public Map<String, ArrayList<String>> getColumns()
    {
        return _columns;
    }

    /**
     * Gets the number of this instance either relative to other pages in the 
     * CSV file, or relative to other sections in the generated report
     * @return the _number
     */
    public int getNumber()
    {
        return _number;
    }

    /**
     * Sets the number of this page, either relative to other pages in the 
     * CSV file, or relative to other sections in the generated report
     * @param number the number to assign this page
     */
    public void setNumber(int number)
    {
        this._number = number;
    }

    /**
     * Gets all summary headers, including their data, as defined by Martin 
     * Browers
     * @return the summary headers found in the CSV file
     */
    public Map<String, String> getSummaryHeaders()
    {
        return _summaryHeaders;
    }
    
}
