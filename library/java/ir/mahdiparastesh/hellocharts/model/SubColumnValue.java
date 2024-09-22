package ir.mahdiparastesh.hellocharts.model;

import androidx.annotation.NonNull;

import java.util.Arrays;

import ir.mahdiparastesh.hellocharts.util.ChartUtils;
import ir.mahdiparastesh.hellocharts.view.Chart;

public class SubColumnValue {
    private float value;
    private float originValue;
    private float diff;
    private int color = ChartUtils.DEFAULT_COLOR;
    private int darkenColor = ChartUtils.DEFAULT_DARKEN_COLOR;
    private char[] label;

    public SubColumnValue() {
        setValue(0);
    }

    public SubColumnValue(float value) {
        // point and targetPoint have to be different objects
        setValue(value);
    }

    public SubColumnValue(float value, int color) {
        // point and targetPoint have to be different objects
        setValue(value);
        setColor(color);
    }

    public SubColumnValue(SubColumnValue columnValue) {
        setValue(columnValue.value);
        setColor(columnValue.color);
        this.label = columnValue.label;
    }

    public void update(float scale) {
        value = originValue + diff * scale;
    }

    public void finish() {
        setValue(originValue + diff);
    }

    public float getValue() {
        return value;
    }

    public SubColumnValue setValue(float value) {
        this.value = value;
        this.originValue = value;
        this.diff = 0;
        return this;
    }

    /**
     * Set target value that should be reached when data animation finish then call {@link Chart#startDataAnimation()}
     */
    public SubColumnValue setTarget(float target) {
        setValue(value);
        this.diff = target - originValue;
        return this;
    }

    public int getColor() {
        return color;
    }

    public SubColumnValue setColor(int color) {
        this.color = color;
        this.darkenColor = ChartUtils.darkenColor(color);
        return this;
    }

    public int getDarkenColor() {
        return darkenColor;
    }

    //@Deprecated
    public char[] getLabel() {
        return label;
    }

    public SubColumnValue setLabel(String label) {
        this.label = label.toCharArray();
        return this;
    }

    public char[] getLabelAsChars() {
        return label;
    }

    //@Deprecated
    public SubColumnValue setLabel(char[] label) {
        this.label = label;
        return this;
    }

    @NonNull
    @Override
    public String toString() {
        return "ColumnValue [value=" + value + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubColumnValue that = (SubColumnValue) o;

        if (color != that.color) return false;
        if (darkenColor != that.darkenColor) return false;
        if (Float.compare(that.diff, diff) != 0) return false;
        if (Float.compare(that.originValue, originValue) != 0) return false;
        if (Float.compare(that.value, value) != 0) return false;
        return Arrays.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        int result = (value != 0f ? Float.floatToIntBits(value) : 0);
        result = 31 * result + (originValue != 0f ? Float.floatToIntBits(originValue) : 0);
        result = 31 * result + (diff != 0f ? Float.floatToIntBits(diff) : 0);
        result = 31 * result + color;
        result = 31 * result + darkenColor;
        result = 31 * result + (label != null ? Arrays.hashCode(label) : 0);
        return result;
    }
}
