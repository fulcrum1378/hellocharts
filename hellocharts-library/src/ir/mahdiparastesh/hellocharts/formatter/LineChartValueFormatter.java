package ir.mahdiparastesh.hellocharts.formatter;

import ir.mahdiparastesh.hellocharts.model.PointValue;

public interface LineChartValueFormatter {

    int formatChartValue(char[] formattedValue, PointValue value);
}
