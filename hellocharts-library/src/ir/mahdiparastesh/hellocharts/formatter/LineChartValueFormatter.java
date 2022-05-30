package ir.mahdiparastesh.hellocharts.formatter;


import ir.mahdiparastesh.hellocharts.model.PointValue;

public interface LineChartValueFormatter {

    public int formatChartValue(char[] formattedValue, PointValue value);
}
