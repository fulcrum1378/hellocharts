package ir.mahdiparastesh.hellocharts.listener;


import ir.mahdiparastesh.hellocharts.model.PointValue;
import ir.mahdiparastesh.hellocharts.model.SubcolumnValue;

public interface ComboLineColumnChartOnValueSelectListener extends OnValueDeselectListener {

    void onColumnValueSelected(int columnIndex, int subColumnIndex, SubcolumnValue value);

    void onPointValueSelected(int lineIndex, int pointIndex, PointValue value);
}
