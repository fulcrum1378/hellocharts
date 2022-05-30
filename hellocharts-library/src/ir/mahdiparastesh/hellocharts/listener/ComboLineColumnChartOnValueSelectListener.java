package ir.mahdiparastesh.hellocharts.listener;


import ir.mahdiparastesh.hellocharts.model.PointValue;
import ir.mahdiparastesh.hellocharts.model.SubColumnValue;

public interface ComboLineColumnChartOnValueSelectListener extends OnValueDeselectListener {

    void onColumnValueSelected(int columnIndex, int subColumnIndex, SubColumnValue value);

    void onPointValueSelected(int lineIndex, int pointIndex, PointValue value);
}
