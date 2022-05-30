package ir.mahdiparastesh.hellocharts.formatter;

import ir.mahdiparastesh.hellocharts.model.SubcolumnValue;

public interface ColumnChartValueFormatter {

    public int formatChartValue(char[] formattedValue, SubcolumnValue value);

}
