import * as am4core from "@amcharts/amcharts4/core";
import * as am4charts from "@amcharts/amcharts4/charts";
import am4themes_animated from "@amcharts/amcharts4/themes/animated";


if ($('#radar-chart').length > 0) {

    am4core.useTheme(am4themes_animated);
    let chart = am4core.create("radar-chart", am4charts.RadarChart);

    let categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());

    categoryAxis.dataFields.category = "metric";

    let valueAxis = chart.yAxes.push(new am4charts.ValueAxis());

    let series = chart.series.push(new am4charts.RadarColumnSeries());
    series.name = "True Sense";
    // series.bullets.push(new am4charts.CircleBullet());
    series.strokeWidth = 3;
    series.dataFields.valueY = "value";
    series.dataFields.categoryX = "metric";
    series.strokeWidth = 0;
    series.columns.template.tooltipText = "Series: {name}\nCategory: {categoryX}\nValue: {valueY}";
    series.sequencedInterpolation = true;
    series.sequencedInterpolationDelay = 100;
    series.stacked = true;

    let series2 = chart.series.push(new am4charts.RadarColumnSeries());
    series2.dataFields.valueY = "userValue";
    series2.dataFields.categoryX = "metric";
    series2.name = "User Ratings";
    series2.strokeWidth = 0;
    series2.sequencedInterpolation = true;
    series2.columns.template.tooltipText = "Series: {name}\nCategory: {categoryX}\nValue: {valueY}";
    series2.sequencedInterpolationDelay = 100;
    series2.stacked = true;




    chart.cursor = new am4charts.RadarCursor();
    chart.legend = new am4charts.Legend();

    // chart.startAngle = -170;
    // chart.endAngle = -10;
    // chart.innerRadius = am4core.percent(50);
    chart.categoryField = "metric";

    chart.data = [
        {
            "metric": "Safety",
            "value": 4.2,
            "userValue": 4.0
        },
        {
            "metric": "Education",
            "value": 4.4,
            "userValue": 4.0
        },
        {
            "metric": "Growth",
            "value": 2.5,
            "userValue": 4.0
        },
        {
            "metric": "Employment",
            "value": 3.2,
            "userValue": 4.0
        },
        {
            "metric": "Health",
            "value": 4.6,
            "userValue": 4.0
        }
    ];


}




