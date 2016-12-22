/*
 * Author: Mohamed Alie Pussah, II
 *   Date: Nov 30, 2016
 */
package edu.wcupa.cscclub.projects.martinbrower.reportgenerator.model;

import java.util.ArrayList;

/**
 *
 */
public class Report
{

    private Integer _caseCount;
    private final ArrayList<Page> _manifests;
    private final Page _page;

    public Report(Page page) {
        _page = page;
        _manifests = new ArrayList<>();
        _caseCount = null;
    }

    public int getCaseCount() {
        if (_caseCount != null) {
            return _caseCount;
        }
        
        if (_page.COLUMNS.isEmpty()) {
            return 0;
        }

        int caseCount = 0;
        final int EXPECTED_INDEX_FOR_CASES_COLUMN = 1;
        if (_page.COLUMNS.size() > 1) {
            Column column = _page.COLUMNS.get(EXPECTED_INDEX_FOR_CASES_COLUMN);
            if (column.HEADER.VALUE.toLowerCase().equals("cases")) {
                if (column.CELLS.isEmpty()) {
                    return 0;
                }

                for (Cell cell : column.CELLS) {
                    caseCount += Integer.parseInt(cell.VALUE);
                }
                _caseCount = caseCount;
                return _caseCount;
            }
        }

        for (Column column : _page.COLUMNS) {
            if (column.HEADER.VALUE.toLowerCase().equals("cases")) {
                for (Cell cell : column.CELLS) {
                    caseCount += Integer.parseInt(cell.VALUE);
                }
                _caseCount = caseCount;
            }
        }
        return _caseCount;
    }
}
