package ir.mahdiparastesh.hellocharts.provider;

import ir.mahdiparastesh.hellocharts.model.LineChartData;

public interface LineChartDataProvider {

    LineChartData getLineChartData();

    void setLineChartData(LineChartData data);
}
