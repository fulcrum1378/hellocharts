package ir.mahdiparastesh.hellocharts.listener;


import ir.mahdiparastesh.hellocharts.model.SubColumnValue;

public interface ColumnChartOnValueSelectListener extends OnValueDeselectListener {

    void onValueSelected(int columnIndex, int subColumnIndex, SubColumnValue value);

}
