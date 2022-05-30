package ir.mahdiparastesh.hellocharts.provider;

import ir.mahdiparastesh.hellocharts.model.PieChartData;

public interface PieChartDataProvider {

    PieChartData getPieChartData();

    void setPieChartData(PieChartData data);
}
