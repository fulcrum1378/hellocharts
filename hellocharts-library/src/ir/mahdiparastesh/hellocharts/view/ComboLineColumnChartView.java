package ir.mahdiparastesh.hellocharts.view;

import android.content.Context;
import android.util.AttributeSet;

import ir.mahdiparastesh.hellocharts.listener.ComboLineColumnChartOnValueSelectListener;
import ir.mahdiparastesh.hellocharts.listener.DummyCompoLineColumnChartOnValueSelectListener;
import ir.mahdiparastesh.hellocharts.model.ChartData;
import ir.mahdiparastesh.hellocharts.model.ColumnChartData;
import ir.mahdiparastesh.hellocharts.model.ComboLineColumnChartData;
import ir.mahdiparastesh.hellocharts.model.LineChartData;
import ir.mahdiparastesh.hellocharts.model.PointValue;
import ir.mahdiparastesh.hellocharts.model.SelectedValue;
import ir.mahdiparastesh.hellocharts.model.SelectedValue.SelectedValueType;
import ir.mahdiparastesh.hellocharts.model.SubcolumnValue;
import ir.mahdiparastesh.hellocharts.provider.ColumnChartDataProvider;
import ir.mahdiparastesh.hellocharts.provider.ComboLineColumnChartDataProvider;
import ir.mahdiparastesh.hellocharts.provider.LineChartDataProvider;
import ir.mahdiparastesh.hellocharts.renderer.ColumnChartRenderer;
import ir.mahdiparastesh.hellocharts.renderer.ComboLineColumnChartRenderer;
import ir.mahdiparastesh.hellocharts.renderer.LineChartRenderer;

public class ComboLineColumnChartView extends AbstractChartView
        implements ComboLineColumnChartDataProvider {
    protected ComboLineColumnChartData data;
    protected ColumnChartDataProvider columnChartDataProvider = new ComboColumnChartDataProvider();
    protected LineChartDataProvider lineChartDataProvider = new ComboLineChartDataProvider();
    protected ComboLineColumnChartOnValueSelectListener onValueTouchListener = new
            DummyCompoLineColumnChartOnValueSelectListener();

    public ComboLineColumnChartView(Context context) {
        this(context, null, 0);
    }

    public ComboLineColumnChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ComboLineColumnChartView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setChartRenderer(new ComboLineColumnChartRenderer(context, this, columnChartDataProvider,
                lineChartDataProvider));
        setComboLineColumnChartData(ComboLineColumnChartData.generateDummyData());
    }

    @Override
    public ComboLineColumnChartData getComboLineColumnChartData() {
        return data;
    }

    @Override
    public void setComboLineColumnChartData(ComboLineColumnChartData data) {
        this.data = data;
        super.onChartDataChange();
    }

    @Override
    public ChartData getChartData() {
        return data;
    }

    @Override
    public void callTouchListener() {
        SelectedValue selectedValue = chartRenderer.getSelectedValue();

        if (selectedValue.isSet()) {
            if (SelectedValueType.COLUMN.equals(selectedValue.getType())) {
                SubcolumnValue value = data.getColumnChartData().getColumns().get(selectedValue.getFirstIndex())
                        .getValues().get(selectedValue.getSecondIndex());
                onValueTouchListener.onColumnValueSelected(selectedValue.getFirstIndex(),
                        selectedValue.getSecondIndex(), value);

            } else if (SelectedValueType.LINE.equals(selectedValue.getType())) {
                PointValue value = data.getLineChartData()
                        .getLines()
                        .get(selectedValue.getFirstIndex())
                        .getValues()
                        .get(selectedValue.getSecondIndex());
                onValueTouchListener.onPointValueSelected(selectedValue.getFirstIndex(),
                        selectedValue.getSecondIndex(), value);

            } else throw new IllegalArgumentException(
                    "Invalid selected value type " + selectedValue.getType().name());
        } else onValueTouchListener.onValueDeselected();
    }

    public ComboLineColumnChartOnValueSelectListener getOnValueTouchListener() {
        return onValueTouchListener;
    }

    public void setOnValueTouchListener(ComboLineColumnChartOnValueSelectListener touchListener) {
        if (null != touchListener) this.onValueTouchListener = touchListener;
    }

    public void setColumnChartRenderer(Context context, ColumnChartRenderer columnChartRenderer) {
        setChartRenderer(new ComboLineColumnChartRenderer(context, this, columnChartRenderer, lineChartDataProvider));
    }

    public void setLineChartRenderer(Context context, LineChartRenderer lineChartRenderer) {
        setChartRenderer(new ComboLineColumnChartRenderer(context, this, columnChartDataProvider, lineChartRenderer));
    }

    private class ComboLineChartDataProvider implements LineChartDataProvider {

        @Override
        public LineChartData getLineChartData() {
            return ComboLineColumnChartView.this.data.getLineChartData();
        }

        @Override
        public void setLineChartData(LineChartData data) {
            ComboLineColumnChartView.this.data.setLineChartData(data);
        }
    }

    private class ComboColumnChartDataProvider implements ColumnChartDataProvider {
        @Override
        public ColumnChartData getColumnChartData() {
            return ComboLineColumnChartView.this.data.getColumnChartData();
        }

        @Override
        public void setColumnChartData(ColumnChartData data) {
            ComboLineColumnChartView.this.data.setColumnChartData(data);
        }
    }
}
