package ir.mahdiparastesh.hellocharts.renderer;

import android.content.Context;

import ir.mahdiparastesh.hellocharts.provider.ColumnChartDataProvider;
import ir.mahdiparastesh.hellocharts.provider.LineChartDataProvider;
import ir.mahdiparastesh.hellocharts.view.Chart;

public class ComboLineColumnChartRenderer extends ComboChartRenderer {

    public ComboLineColumnChartRenderer(Context context, Chart chart, ColumnChartDataProvider columnChartDataProvider,
                                        LineChartDataProvider lineChartDataProvider) {
        this(context, chart, new ColumnChartRenderer(context, chart, columnChartDataProvider),
                new LineChartRenderer(context, chart, lineChartDataProvider));
    }

    public ComboLineColumnChartRenderer(Context context, Chart chart, ColumnChartRenderer columnChartRenderer,
                                        LineChartDataProvider lineChartDataProvider) {
        this(context, chart, columnChartRenderer, new LineChartRenderer(context, chart, lineChartDataProvider));
    }

    public ComboLineColumnChartRenderer(Context context, Chart chart, ColumnChartDataProvider columnChartDataProvider,
                                        LineChartRenderer lineChartRenderer) {
        this(context, chart, new ColumnChartRenderer(context, chart, columnChartDataProvider), lineChartRenderer);
    }

    public ComboLineColumnChartRenderer(Context context, Chart chart, ColumnChartRenderer columnChartRenderer,
                                        LineChartRenderer lineChartRenderer) {
        super(context, chart);

        renderers.add(columnChartRenderer);
        renderers.add(lineChartRenderer);
    }
}