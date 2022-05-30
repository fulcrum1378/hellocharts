package ir.mahdiparastesh.hellocharts.formatter;

import ir.mahdiparastesh.hellocharts.model.BubbleValue;

public interface BubbleChartValueFormatter {

    int formatChartValue(char[] formattedValue, BubbleValue value);
}
