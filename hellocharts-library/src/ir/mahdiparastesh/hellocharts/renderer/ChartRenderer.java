package ir.mahdiparastesh.hellocharts.renderer;

import android.graphics.Canvas;

import ir.mahdiparastesh.hellocharts.model.SelectedValue;
import ir.mahdiparastesh.hellocharts.model.Viewport;

public interface ChartRenderer {

    void onChartSizeChanged();

    void onChartDataChanged();

    void onChartViewportChanged();

    void resetRenderer();

    void draw(Canvas canvas);

    /**
     * Draw chart data that should not be clipped to contentRect area.
     */
    void drawUnClipped(Canvas canvas);

    /**
     * Checks if given pixel coordinates corresponds to any chart value. If yes return true and set selectedValue, if
     * not selectedValue should be *cleared* and method should return false.
     */
    boolean checkTouch(float touchX, float touchY);

    /**
     * Returns true if there is value selected.
     */
    boolean isTouched();

    /**
     * Clear value selection.
     */
    void clearTouch();

    Viewport getMaximumViewport();

    void setMaximumViewport(Viewport maxViewport);

    Viewport getCurrentViewport();

    void setCurrentViewport(Viewport viewport);

    boolean isViewportCalculationEnabled();

    void setViewportCalculationEnabled(boolean isEnabled);

    void selectValue(SelectedValue selectedValue);

    SelectedValue getSelectedValue();
}
