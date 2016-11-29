/*
 * Written by WCUPA Computer Science club members 
 * November 2016
 */
package edu.wcupa.cscclub.projects.martinbrower.reportgenerator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohamed Alie Pussah (mp754927@wcupa.edu)
 * Encapsulates data for a single page; either 
 * from a parsed CSV file, or from a generated 
 * report
 */
public class Page
{
    private int _number;
    private int _cartTotal;    
    private final ArrayList<Column> _columns;
    
    /**
     * The summary rows on this page
     */
    public final Map<String, String> SUMMARIES;
    
    /**
     * Creates a new page in a report or in a parsed CSV file
     */
    public Page() {
        SUMMARIES = new HashMap<>();
        _columns = new ArrayList<>();
    }
    
    /**
     * Gets the CART TOTAL as read from the CSV file
     * @return The CART TOTAL as read from the CSV file
     */
    public int getCartTotal()
    {
        return _cartTotal;
    }

    /**
     * Sets the CART TOTAL as read from the CSV file
     * @param cartTotal The CART TOTAL as read from the CSV file
     */
    public void setCartTotal(int cartTotal)
    {
        this._cartTotal = cartTotal;
    }
    
    /**
     * Gets all required columns based on the specified needed columns
     * @return The required columns from the CSV file, sorted in 
     * ascending order of their header cells
     */
    public ArrayList<Column> getColumns()
    {
        Collections.sort(_columns);
        return _columns;
    }

    /**
     * Gets the cardinal number of this page. If this is the last page in 
     * a CSV file, this value represents the number of pages in that CSV 
     * file
     * @return The cardinal number of this page
     */
    public int getNumber()
    {
        return _number;
    }

    /**
     * Sets the cardinal number of this page
     * @param number The cardinal number of this page
     */
    public void setNumber(int number)
    {
        this._number = number;
    }    
}
