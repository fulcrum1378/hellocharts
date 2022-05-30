package ir.mahdiparastesh.hellocharts.listener;


import ir.mahdiparastesh.hellocharts.model.SubcolumnValue;

public interface ColumnChartOnValueSelectListener extends OnValueDeselectListener {

    void onValueSelected(int columnIndex, int subColumnIndex, SubcolumnValue value);

}
