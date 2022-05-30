package ir.mahdiparastesh.hellocharts.listener;

import ir.mahdiparastesh.hellocharts.model.SubColumnValue;

public class DummyColumnChartOnValueSelectListener implements ColumnChartOnValueSelectListener {

    @Override
    public void onValueSelected(int columnIndex, int subColumnIndex, SubColumnValue value) {
    }

    @Override
    public void onValueDeselected() {
    }
}
