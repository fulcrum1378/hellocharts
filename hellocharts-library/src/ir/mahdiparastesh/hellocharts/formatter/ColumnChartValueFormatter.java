package ir.mahdiparastesh.hellocharts.formatter;

import ir.mahdiparastesh.hellocharts.model.SubColumnValue;

public interface ColumnChartValueFormatter {

    public int formatChartValue(char[] formattedValue, SubColumnValue value);

}
