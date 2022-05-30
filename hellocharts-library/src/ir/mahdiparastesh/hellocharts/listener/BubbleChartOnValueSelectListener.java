package ir.mahdiparastesh.hellocharts.listener;


import ir.mahdiparastesh.hellocharts.model.BubbleValue;

public interface BubbleChartOnValueSelectListener extends OnValueDeselectListener {

    void onValueSelected(int bubbleIndex, BubbleValue value);
}
