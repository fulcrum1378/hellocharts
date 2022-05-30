package ir.mahdiparastesh.hellocharts.view;

import android.content.Context;
import android.util.AttributeSet;

import ir.mahdiparastesh.hellocharts.listener.ColumnChartOnValueSelectListener;
import ir.mahdiparastesh.hellocharts.listener.DummyColumnChartOnValueSelectListener;
import ir.mahdiparastesh.hellocharts.model.ColumnChartData;
import ir.mahdiparastesh.hellocharts.model.SelectedValue;
import ir.mahdiparastesh.hellocharts.model.SubColumnValue;
import ir.mahdiparastesh.hellocharts.provider.ColumnChartDataProvider;
import ir.mahdiparastesh.hellocharts.renderer.ColumnChartRenderer;

public class ColumnChartView extends AbstractChartView implements ColumnChartDataProvider {

    private ColumnChartData data;
    private ColumnChartOnValueSelectListener onValueTouchListener = new DummyColumnChartOnValueSelectListener();

    public ColumnChartView(Context context) {
        this(context, null, 0);
    }

    public ColumnChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColumnChartView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setChartRenderer(new ColumnChartRenderer(context, this, this));
        setColumnChartData(ColumnChartData.generateDummyData());
    }

    @Override
    public ColumnChartData getColumnChartData() {
        return data;
    }

    @Override
    public void setColumnChartData(ColumnChartData data) {
        if (null == data) this.data = ColumnChartData.generateDummyData();
        else this.data = data;

        super.onChartDataChange();

    }

    @Override
    public ColumnChartData getChartData() {
        return data;
    }

    @Override
    public void callTouchListener() {
        SelectedValue selectedValue = chartRenderer.getSelectedValue();

        if (selectedValue.isSet()) {
            SubColumnValue value = data.getColumns().get(selectedValue.getFirstIndex()).getValues()
                    .get(selectedValue.getSecondIndex());
            onValueTouchListener.onValueSelected(selectedValue.getFirstIndex(), selectedValue.getSecondIndex(), value);
        } else {
            onValueTouchListener.onValueDeselected();
        }
    }

    public ColumnChartOnValueSelectListener getOnValueTouchListener() {
        return onValueTouchListener;
    }

    public void setOnValueTouchListener(ColumnChartOnValueSelectListener touchListener) {
        if (null != touchListener)
            this.onValueTouchListener = touchListener;
    }
}
