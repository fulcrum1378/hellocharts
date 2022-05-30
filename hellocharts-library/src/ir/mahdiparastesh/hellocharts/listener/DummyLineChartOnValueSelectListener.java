package ir.mahdiparastesh.hellocharts.listener;

import ir.mahdiparastesh.hellocharts.model.PointValue;

public class DummyLineChartOnValueSelectListener implements LineChartOnValueSelectListener {

    @Override
    public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
    }

    @Override
    public void onValueDeselected() {
    }
}
