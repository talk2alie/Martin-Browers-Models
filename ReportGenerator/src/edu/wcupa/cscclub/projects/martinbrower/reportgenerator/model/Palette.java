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

    /**
     * Gets a list of all cases that are in this palette
     */
    public final ArrayList<Cases> CASES;
    private int _caseCount; // Number of cases in palette
    private final int MAX_CASE_COUNT = 21;
    private int _referencePage;

    /**
     * Gets the position within the trailer where this palette will be placed
     */
    public final String TRAILER_POSITION;

    /**
     * Gets a unique identifier for this palette
     */
    public final String ID;

    /**
     * Create a new palette in the given trailer position
     *
     * @param trailerPosition - The position within the trailer where
     *                        this palette will be positioned
     */
    public Palette(String trailerPosition) {
        TRAILER_POSITION = trailerPosition;
        ID = trailerPosition + UUID.randomUUID().toString();
        CASES = new ArrayList<>(21);
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
            if ((_caseCount + cases.getQuantity()) <= MAX_CASE_COUNT) {
                cases.moveToPalette(ID);
                CASES.add(cases);
                _caseCount += cases.getQuantity();
                return null;
            }
            int acceptableCasesCount = MAX_CASE_COUNT - _caseCount;
            Cases acceptableCases = new Cases(cases.getRoute(),
                    cases.getStop(), acceptableCasesCount, cases.getContent(),
                    cases.getContentId(), ID);
            CASES.add(acceptableCases);
            _caseCount += acceptableCasesCount;
            cases.decreaseQuantityBy(acceptableCasesCount);
            return cases;
        }

        // These cases already exist in this palette
        int acceptableCasesCount = MAX_CASE_COUNT - _caseCount;
        entry.increaseQuantityBy(acceptableCasesCount);
        CASES.set(index.get(), entry); // update the cases in the palette        
        _caseCount += acceptableCasesCount;
        cases.decreaseQuantityBy(acceptableCasesCount);
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
        CASES.set(index.get(), entry);
        return new Cases(entry.getRoute(), entry.getStop(), count,
                entry.getContent(), entry.getContentId(), "");
    }

    /**
     * Searches this palette for cases with the given casesId
     *
     * @param casesId - The unique identifier of the cases to find
     * @param index   - The starting position within the palette (counting from
     *                0, top to bottom), where these cases can be found
     * @return
     */
    public Cases findCases(String casesId, AtomicReference<Integer> index) {
        for (int i = 0; i < CASES.size(); ++i) {
            if (CASES.get(i).getId().equals(casesId)) {
                index.set(i);
                return CASES.get(i);
            }
        }
        return null;
    }

    /**
     * Gets the page from the CSV file where data for this palette
     * was collected
     *
     * @return the page, in the CSV file, from which data for this
     *         palette was collected
     */
    public int getReferencePage() {
        return _referencePage;
    }

    /**
     * Sets the page in the CSV file from which data for this palette
     * was collected
     *
     * @param referencePage - The page in the CSV file where data for this
     *                      palette was collected
     */
    public void setReferencePage(int referencePage) {
        _referencePage = referencePage;
    }
}
