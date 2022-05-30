package ir.mahdiparastesh.hellocharts.listener;

import ir.mahdiparastesh.hellocharts.model.PointValue;

public interface LineChartOnValueSelectListener extends OnValueDeselectListener {

    void onValueSelected(int lineIndex, int pointIndex, PointValue value);
}
