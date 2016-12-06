/*
 * Author: Mohamed Alie Pussah, II
 *   Date: Dec 5, 2016
 */
package edu.wcupa.cscclub.projects.martinbrower.reportgenerator.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * A palette containing one or more cases to be delivered to a route
 */
public class Palette
{

    /**
     * Gets a unique identification of this palette
     */
    public final String Id;

    /**
     * Gets the identification number of the route where this
     * palette should be delivered
     */
    public final int RouteNumber;

    /**
     * Gets the position where this palette will be placed
     * within a trailer
     */
    public final String TrailerPosition;

    private final ArrayList<Case> _cases;
    private int _casesCount;
    private final int MAX_CASE_COUNT;

    /**
     * @param routeNumber     The ID of the route where this palette
     *                        will be delivered
     * @param trailerPosition The position within a trailer where
     *                        this palette will be placed
     */
    public Palette(int routeNumber, String trailerPosition) {
        MAX_CASE_COUNT = 21;
        Id = UUID.randomUUID().toString() + "_" + trailerPosition;
        RouteNumber = routeNumber;
        TrailerPosition = trailerPosition;
        _cases = new ArrayList<>(MAX_CASE_COUNT);
    }

    /**
     * Gets the number of cases stacked in this palette
     *
     * @return The number of cases in this palette
     */
    public int getCasesCount() {
        return _casesCount;
    }

    /**
     * Gets all the cases that are stacked in this palette
     *
     * @return The cases that are stacked in this palette
     */
    public ArrayList<Case> getCases() {
        return _cases;
    }

    /**
     * Gets a value that indicates whether it is safe to add the given case
     * to this palette
     *
     * @param caseToAdd - The case to be added to this palette
     * @return True if the sum of the quantities of the cases in this palette
     *         added to the quantity of the given case is less than 21; otherwise,
     *         False
     */
    public boolean canAddCase(Case caseToAdd) {
        return MAX_CASE_COUNT > _casesCount + caseToAdd.getQuantity();
    }

    /**
     * Adds the given case to this palette if the palette has space
     * for another case
     *
     * @param paletteCase - The case to add to this palette
     * @exception ArrayIndexOutOfBoundsException - Thrown to indicate that this
     * palette is full. Note that this exception will also be thrown if the
     * quantity of the given case added to the sum of the quantities of each
     * case within this palette exceeds or equals 21
     */
    public void addCase(Case paletteCase) {
        if (!canAddCase(paletteCase)) {
            throw new ArrayIndexOutOfBoundsException("The palette is full");
        }
        
        int index = findCase(paletteCase.ItemNumber);
        if(index >= 0){
            Case caseToUpdate = _cases.get(index);
            caseToUpdate.IncreaseQuantityBy(paletteCase.getQuantity());
            if(!caseToUpdate.getPaletteId().equalsIgnoreCase(paletteCase.getPaletteId())){
                caseToUpdate.moveToPalette(Id);
            }
            _cases.set(index, caseToUpdate);
            _casesCount += paletteCase.getQuantity();
            return;
        }
        _cases.add(paletteCase);
        _casesCount += paletteCase.getQuantity();
    }

    /**
     * Finds a case within this palette that has the given WRIN and
     * reduces its quantity by the value of count
     *
     * @param wrin  - The WRIN of the case to remove
     * @param count - The value by which to reduce the quantity of the given
     *              case
     * @return A newly generated case representing the case that was
     *         removed from the palette. If the given WRIN is not found within the
     *         palette, the returned value is null
     */
    public Case removeCase(String wrin, int count) {
        Case removedCase = null;
        int index = findCase(wrin);

        if (index >= 0) {
            Case caseToUpdate = _cases.get(index);
            if (caseToUpdate.getQuantity() < count) {
                throw new ArithmeticException("The operation cannot be "
                        + "completed because there are less items than you "
                        + "want to remove");
            }
            caseToUpdate.DecreaseQuantityBy(count);
            removedCase = _cases.set(index, caseToUpdate);
            removedCase.DecreaseQuantityBy(removedCase.getQuantity());
            removedCase.IncreaseQuantityBy(count);
        }
        return removedCase;
    }

    private int findCase(String wrin) {
        int foundIndex = -1;
        for (int i = 0; i < _cases.size(); ++i) {
            if (_cases.get(i).Description.equalsIgnoreCase(wrin)) {
                foundIndex = i;
                break;
            }
        }
        return foundIndex;
    }
}
