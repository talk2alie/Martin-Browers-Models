/*
 * Author: Mohamed Alie Pussah, II
 *   Date: Dec 5, 2016
 */
package edu.wcupa.cscclub.projects.martinbrower.reportgenerator.model;

/**
 * A vector that contains information about cases of a particular
 * item in a palette
 */
public class Case implements Comparable<Case>
{
    /**
     * Gets the ID of the stop where this case will be delivered
     */
    public final String Stop;

    /**
     * Gets the description or name of the items within this case
     */
    public final String Description;

    /**
     * Gets the Worldwide Raw Item Number (WRIN) of the items
     * contain within this case
     */
    public final String ItemNumber;

    /**
     * Gets the unique identifier of the palette to which this case belongs
     */
    private String _paletteId;

    public int _quantity;

    /**
     * Creates an instance of a case with the provided information
     *
     * @param stop        - The ID of the stop to which this case will be
     *                    delivered
     * @param description - A friendly name for the items in this case
     * @param itemNumber  - The WRIN, or unique ID, of the items in this case
     * @param quantity    - The total number of this case that belongs to a
     *                    particular palette
     * @param paletteId   - The unique ID of the palette to which this case
     *                    belongs
     */
    public Case(String stop, String description, String itemNumber, int quantity, String paletteId) {
        Stop = stop;
        Description = description;
        ItemNumber = itemNumber;
        _quantity = quantity;
        _paletteId = paletteId;
    }

    /**
     * Compares the stop of this case to the stop of the other case
     *
     * @param other - The other case to which to compare this case
     * @return Negative integer, zero, or a positive integer as
     *         this case is less than, equal to, or greater than the
     *         specified object
     */
    @Override
    public int compareTo(Case other) {
        return Stop.compareTo(other.Stop);
    }

    /**
     * Increases the quantity of this case in its containing palette
     *
     * @param count - The number by which to increase the quantity
     *              of this case within a given palette
     */
    public void IncreaseQuantityBy(int count) {
        _quantity += count;
    }

    /**
     * Decreases the quantity of this case in its containing palette
     *
     * @param count - The number by which to decrease the quantity
     *              of this case within a given palette
     */
    public void DecreaseQuantityBy(int count) {
        _quantity += count;
    }

    /**
     * Gets the quantity of this case that is contained within
     * a palette
     *
     * @return Quantity of this case within a palette
     */
    public int getQuantity() {
        return _quantity;
    }

    /**
     * Gets the ID of the palette to which this case belongs
     *
     * @return The ID of the case's palette
     */
    public String getPaletteId() {
        return _paletteId;
    }

    /**
     * Moves this case to the palette with the given ID
     *
     * @param paletteId - The ID of the palette to which this case should
     *                  be moved
     */
    public void moveToPalette(String paletteId) {
        _paletteId = paletteId;
    }
}
