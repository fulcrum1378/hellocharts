package ir.mahdiparastesh.hellocharts.listener;

import ir.mahdiparastesh.hellocharts.model.SliceValue;

public interface PieChartOnValueSelectListener extends OnValueDeselectListener {

    void onValueSelected(int arcIndex, SliceValue value);
}
