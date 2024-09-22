package ir.mahdiparastesh.hellocharts.renderer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.RectF;
import android.graphics.Typeface;

import ir.mahdiparastesh.hellocharts.calculator.ChartCalculator;
import ir.mahdiparastesh.hellocharts.model.ChartData;
import ir.mahdiparastesh.hellocharts.model.SelectedValue;
import ir.mahdiparastesh.hellocharts.model.Viewport;
import ir.mahdiparastesh.hellocharts.util.ChartUtils;
import ir.mahdiparastesh.hellocharts.view.Chart;

public abstract class AbstractChartRenderer implements ChartRenderer {
    public int DEFAULT_LABEL_MARGIN_DP = 4;

    protected Chart chart;
    protected ChartCalculator calculator;
    protected Paint labelPaint = new Paint();
    protected Paint labelBackgroundPaint = new Paint();
    protected RectF labelBackgroundRect = new RectF();
    protected FontMetricsInt fontMetrics = new FontMetricsInt();

    protected boolean isViewportCalculationEnabled = true;
    protected float density;
    protected float scaledDensity;
    protected SelectedValue selectedValue = new SelectedValue();
    protected char[] labelBuffer = new char[64];
    protected int labelOffset;
    protected int labelMargin;
    protected boolean isValueLabelBackgroundEnabled;
    protected boolean isValueLabelBackgroundAuto;

    public AbstractChartRenderer(Context context, Chart chart) {
        this.density = context.getResources().getDisplayMetrics().density;
        this.scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        this.chart = chart;
        this.calculator = chart.getChartCalculator();

        labelMargin = ChartUtils.dp2px(density, DEFAULT_LABEL_MARGIN_DP);
        labelOffset = labelMargin;

        labelPaint.setAntiAlias(true);
        labelPaint.setStyle(Paint.Style.FILL);
        labelPaint.setTextAlign(Align.LEFT);
        labelPaint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        labelPaint.setColor(Color.WHITE);

        labelBackgroundPaint.setAntiAlias(true);
        labelBackgroundPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void resetRenderer() {
        this.calculator = chart.getChartCalculator();
    }

    @Override
    public void onChartDataChanged() {
        final ChartData data = chart.getChartData();

        Typeface typeface = chart.getChartData().getValueLabelTypeface();
        if (null != typeface) {
            labelPaint.setTypeface(typeface);
        }

        labelPaint.setColor(data.getValueLabelTextColor());
        labelPaint.setTextSize(ChartUtils.sp2px(scaledDensity, data.getValueLabelTextSize()));
        labelPaint.getFontMetricsInt(fontMetrics);

        this.isValueLabelBackgroundEnabled = data.isValueLabelBackgroundEnabled();
        this.isValueLabelBackgroundAuto = data.isValueLabelBackgroundAuto();
        this.labelBackgroundPaint.setColor(data.getValueLabelBackgroundColor());

        // Important - clear selection when data changed.
        selectedValue.clear();

    }

    /**
     * Draws label text and label background if isValueLabelBackgroundEnabled is true.
     */
    protected void drawLabelTextAndBackground(Canvas canvas, char[] labelBuffer, int startIndex, int numChars,
                                              int autoBackgroundColor) {
        final float textX;
        final float textY;

        if (isValueLabelBackgroundEnabled) {

            if (isValueLabelBackgroundAuto) {
                labelBackgroundPaint.setColor(autoBackgroundColor);
            }

            canvas.drawRect(labelBackgroundRect, labelBackgroundPaint);

            textX = labelBackgroundRect.left + labelMargin;
            textY = labelBackgroundRect.bottom - labelMargin;
        } else {
            textX = labelBackgroundRect.left;
            textY = labelBackgroundRect.bottom;
        }

        canvas.drawText(labelBuffer, startIndex, numChars, textX, textY, labelPaint);
    }

    @Override
    public boolean isTouched() {
        return selectedValue.isSet();
    }

    @Override
    public void clearTouch() {
        selectedValue.clear();
    }

    @Override
    public Viewport getMaximumViewport() {
        return calculator.getMaximumViewport();
    }

    @Override
    public void setMaximumViewport(Viewport maxViewport) {
        if (null != maxViewport) {
            calculator.setMaxViewport(maxViewport);
        }
    }

    @Override
    public Viewport getCurrentViewport() {
        return calculator.getCurrentViewport();
    }

    @Override
    public void setCurrentViewport(Viewport viewport) {
        if (null != viewport) {
            calculator.setCurrentViewport(viewport);
        }
    }

    @Override
    public boolean isViewportCalculationEnabled() {
        return isViewportCalculationEnabled;
    }

    @Override
    public void setViewportCalculationEnabled(boolean isEnabled) {
        this.isViewportCalculationEnabled = isEnabled;
    }

    @Override
    public void selectValue(SelectedValue selectedValue) {
        this.selectedValue.set(selectedValue);
    }

    @Override
    public SelectedValue getSelectedValue() {
        return selectedValue;
    }

    @Override
    public void setLabelMargin(int labelMargin) {
        this.labelMargin = labelMargin;
    }

    @Override
    public void setLabelOffset(int labelOffset) {
        this.labelOffset = labelOffset;
    }
}
