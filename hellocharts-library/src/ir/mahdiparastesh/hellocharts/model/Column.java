package ir.mahdiparastesh.hellocharts.model;

import java.util.ArrayList;
import java.util.List;

import ir.mahdiparastesh.hellocharts.formatter.ColumnChartValueFormatter;
import ir.mahdiparastesh.hellocharts.formatter.SimpleColumnChartValueFormatter;
import ir.mahdiparastesh.hellocharts.view.Chart;

public class Column {
    private boolean hasLabels = false;
    private boolean hasLabelsOnlyForSelected = false;
    private ColumnChartValueFormatter formatter = new SimpleColumnChartValueFormatter();
    // TODO: consider Collections.emptyList()
    private List<SubColumnValue> values = new ArrayList<>();

    public Column() {
    }

    public Column(List<SubColumnValue> values) {
        setValues(values);
    }

    public Column(Column column) {
        this.hasLabels = column.hasLabels;
        this.hasLabelsOnlyForSelected = column.hasLabelsOnlyForSelected;
        this.formatter = column.formatter;

        for (SubColumnValue columnValue : column.values) {
            this.values.add(new SubColumnValue(columnValue));
        }
    }

    public void update(float scale) {
        for (SubColumnValue value : values) {
            value.update(scale);
        }

    }

    public void finish() {
        for (SubColumnValue value : values) {
            value.finish();
        }
    }

    public List<SubColumnValue> getValues() {
        return values;
    }

    public Column setValues(List<SubColumnValue> values) {
        if (null == values) {
            this.values = new ArrayList<>();
        } else {
            this.values = values;
        }
        return this;
    }

    public boolean hasLabels() {
        return hasLabels;
    }

    public Column setHasLabels(boolean hasLabels) {
        this.hasLabels = hasLabels;
        if (hasLabels) {
            this.hasLabelsOnlyForSelected = false;
        }
        return this;
    }

    /**
     * @see #setHasLabelsOnlyForSelected(boolean)
     */
    public boolean hasLabelsOnlyForSelected() {
        return hasLabelsOnlyForSelected;
    }

    /**
     * Set true if you want to show value labels only for selected value, works best when chart has
     * isValueSelectionEnabled set to true {@link Chart#setValueSelectionEnabled(boolean)}.
     */
    public Column setHasLabelsOnlyForSelected(boolean hasLabelsOnlyForSelected) {
        this.hasLabelsOnlyForSelected = hasLabelsOnlyForSelected;
        if (hasLabelsOnlyForSelected) {
            this.hasLabels = false;
        }
        return this;
    }

    public ColumnChartValueFormatter getFormatter() {
        return formatter;
    }

    public Column setFormatter(ColumnChartValueFormatter formatter) {
        if (null != formatter) {
            this.formatter = formatter;
        }
        return this;
    }
}
