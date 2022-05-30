package ir.mahdiparastesh.hellocharts.provider;

import ir.mahdiparastesh.hellocharts.model.ColumnChartData;

public interface ColumnChartDataProvider {

    ColumnChartData getColumnChartData();

    void setColumnChartData(ColumnChartData data);
}
