/*
 * Author: Mohamed Alie Pussah, II
 *   Date: Dec 18, 2016
 */
package edu.wcupa.cscclub.projects.martinbrower.reportgenerator.model;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Represents a palette that can hold up to 21 cases of buns
 */
public class Palette
{
    private final ArrayList<Cases> _cases;
    private int _caseCount; // Number of cases in palette
    private final int MAX_CASE_COUNT = 21;
    
    /**
     * The position within the trailer where this palette will be placed
     */
    public final String TRAILER_POSITION;
    
    /**
     * A unique identifier of this palette
     */
    public final String ID;

    /**
     * Create a new palette in the given trailer position
     * @param trailerPosition - The position within the trailer where 
     * this palette will be positioned
     */
    public Palette(String trailerPosition) {
        TRAILER_POSITION = trailerPosition;
        ID = trailerPosition + UUID.randomUUID().toString();
        _cases = new ArrayList<>(21);
    }

    /**
     * Adds the given cases to this palette
     *
     * @param cases - The cases to add to this palette
     * @return null if all the cases were added; otherwise, returns
     *         the cases that could not be added because the palette is full
     */
    public Cases addCases(Cases cases) {
        if (_caseCount >= MAX_CASE_COUNT) {
            return cases;
        }

        AtomicReference<Integer> index = new AtomicReference<>(-1);
        Cases entry = findCases(cases.getId(), index);
        if (entry == null) { // This palette has no entry for these cases
            cases.moveToPalette(ID);
            _cases.add(cases);
            _caseCount += cases.getQuantity();
            return null;
        }

        int acceptableCases = MAX_CASE_COUNT - _caseCount;
        entry.increaseQuantityBy(acceptableCases);
        _cases.set(index.get(), entry); // update the cases in the palette        
        _caseCount += acceptableCases;
        cases.decreaseQuantityBy(acceptableCases);
        return cases;
    }

    /**
     * Removes count of the given cases from this palette
     *
     * @param casesId - The unique identifier of the cases to remove
     * @param count   - The quantity of the cases to remove
     * @return null if the given cases were not found; otherwise, the
     *         removed case
     */
    public Cases removeCases(String casesId, int count) {
        if (_caseCount == 0) {
            return null;
        }

        AtomicReference<Integer> index = new AtomicReference<>(-1);
        Cases entry = findCases(casesId, index);
        if (entry == null) {
            return entry;
        }

        entry.decreaseQuantityBy(count);
        _caseCount -= count;
        _cases.set(index.get(), entry);
        return new Cases(entry.getRoute(), entry.getStop(), count,
                entry.getContent(), entry.getContentId(), entry.getPaletteId());
    }

    /**
     * Searches this palette for cases with the given casesId
     * @param casesId - The unique identifier of the cases to find
     * @param index - The starting position within the palette (counting from 
     * 0, top to bottom), where these cases can be found
     * @return 
     */
    public Cases findCases(String casesId, AtomicReference<Integer> index) {
        for (int i = 0; i < _cases.size(); ++i) {
            if (_cases.get(i).getId().equals(casesId)) {
                index.set(i);
                return _cases.get(i);
            }
        }
        return null;
    }
}
