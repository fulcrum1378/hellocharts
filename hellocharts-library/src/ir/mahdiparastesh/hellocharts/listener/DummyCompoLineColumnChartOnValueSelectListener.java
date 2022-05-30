package ir.mahdiparastesh.hellocharts.listener;

import ir.mahdiparastesh.hellocharts.model.PointValue;
import ir.mahdiparastesh.hellocharts.model.SubcolumnValue;

public class DummyCompoLineColumnChartOnValueSelectListener implements ComboLineColumnChartOnValueSelectListener {

    @Override
    public void onColumnValueSelected(int columnIndex, int subColumnIndex, SubcolumnValue value) {
    }

    @Override
    public void onPointValueSelected(int lineIndex, int pointIndex, PointValue value) {
    }

    @Override
    public void onValueDeselected() {
    }
}
