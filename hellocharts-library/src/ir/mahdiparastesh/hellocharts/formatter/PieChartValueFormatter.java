package ir.mahdiparastesh.hellocharts.formatter;

import ir.mahdiparastesh.hellocharts.model.SliceValue;

public interface PieChartValueFormatter {

    public int formatChartValue(char[] formattedValue, SliceValue value);
}
