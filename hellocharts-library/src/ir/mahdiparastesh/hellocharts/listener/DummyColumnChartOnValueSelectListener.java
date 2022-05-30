package ir.mahdiparastesh.hellocharts.listener;

import ir.mahdiparastesh.hellocharts.model.SubcolumnValue;

public class DummyColumnChartOnValueSelectListener implements ColumnChartOnValueSelectListener {

    @Override
    public void onValueSelected(int columnIndex, int subColumnIndex, SubcolumnValue value) {
    }

    @Override
    public void onValueDeselected() {
    }
}
