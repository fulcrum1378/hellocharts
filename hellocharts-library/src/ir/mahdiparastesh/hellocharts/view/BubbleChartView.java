package ir.mahdiparastesh.hellocharts.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.core.view.ViewCompat;

import ir.mahdiparastesh.hellocharts.listener.BubbleChartOnValueSelectListener;
import ir.mahdiparastesh.hellocharts.listener.DummyBubbleChartOnValueSelectListener;
import ir.mahdiparastesh.hellocharts.model.BubbleChartData;
import ir.mahdiparastesh.hellocharts.model.BubbleValue;
import ir.mahdiparastesh.hellocharts.model.ChartData;
import ir.mahdiparastesh.hellocharts.model.SelectedValue;
import ir.mahdiparastesh.hellocharts.provider.BubbleChartDataProvider;
import ir.mahdiparastesh.hellocharts.renderer.BubbleChartRenderer;

public class BubbleChartView extends AbstractChartView implements BubbleChartDataProvider {
    protected BubbleChartData data;
    protected BubbleChartOnValueSelectListener onValueTouchListener =
            new DummyBubbleChartOnValueSelectListener();

    protected BubbleChartRenderer bubbleChartRenderer;

    public BubbleChartView(Context context) {
        this(context, null, 0);
    }

    public BubbleChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BubbleChartView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        bubbleChartRenderer = new BubbleChartRenderer(context, this, this);
        setChartRenderer(bubbleChartRenderer);
        setBubbleChartData(BubbleChartData.generateDummyData());
    }

    @Override
    public BubbleChartData getBubbleChartData() {
        return data;
    }

    @Override
    public void setBubbleChartData(BubbleChartData data) {
        if (null == data)
            this.data = BubbleChartData.generateDummyData();
        else this.data = data;
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
            BubbleValue value = data.getValues().get(selectedValue.getFirstIndex());
            onValueTouchListener.onValueSelected(selectedValue.getFirstIndex(), value);
        } else {
            onValueTouchListener.onValueDeselected();
        }
    }

    public BubbleChartOnValueSelectListener getOnValueTouchListener() {
        return onValueTouchListener;
    }

    public void setOnValueTouchListener(BubbleChartOnValueSelectListener touchListener) {
        if (null != touchListener) {
            this.onValueTouchListener = touchListener;
        }
    }

    public void removeMargins() {
        bubbleChartRenderer.removeMargins();
        ViewCompat.postInvalidateOnAnimation(this);
    }
}
