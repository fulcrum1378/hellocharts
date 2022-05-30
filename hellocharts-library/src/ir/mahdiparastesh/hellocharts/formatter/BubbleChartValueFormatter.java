package ir.mahdiparastesh.hellocharts.formatter;

import ir.mahdiparastesh.hellocharts.model.BubbleValue;

public interface BubbleChartValueFormatter {

    public int formatChartValue(char[] formattedValue, BubbleValue value);
}
