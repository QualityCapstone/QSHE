import * as am4core from "@amcharts/amcharts4/core";
import * as am4charts from "@amcharts/amcharts4/charts";
import am4themes_animated from "@amcharts/amcharts4/themes/animated";
const api = require('../../lib/local');


am4core.useTheme(am4themes_animated);

if ($('#radar-chart').length > 0) {

    api.getData("state/ratings/average/ID").then(function(avgData) {

        console.log(avgData.metrics);
        let  radarData = avgData.metrics;


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
        series.fill = '#ff9890';

        let series2 = chart.series.push(new am4charts.RadarColumnSeries());
        series2.dataFields.valueY = "userValue";
        series2.dataFields.categoryX = "metric";
        series2.name = "User Ratings";
        series2.strokeWidth = 0;
        series2.sequencedInterpolation = true;
        series2.columns.template.tooltipText = "Series: {name}\nCategory: {categoryX}\nValue: {valueY}";
        series2.sequencedInterpolationDelay = 100;
        series2.stacked = true;
        series2.fill = '#ffcbb5';


        chart.cursor = new am4charts.RadarCursor();
        chart.legend = new am4charts.Legend();

        // chart.startAngle = -170;
        // chart.endAngle = -10;
        // chart.innerRadius = am4core.percent(50);
        chart.categoryField = "metric";

        chart.data = [
            {
                "metric": "Safety",
                "value": 9.2,
                "userValue": radarData['Crime']
            },
            {
                "metric": "Education",
                "value": 8.4,
                "userValue": radarData['Education']
            },
            {
                "metric": "Growth",
                "value": 5.5,
                "userValue": radarData['Growth']
            },
            {
                "metric": "Employment",
                "value": 7.2,
                "userValue": radarData['Employment']
            },
            {
                "metric": "Health",
                "value": 7.6,
                "userValue": radarData['Health']
            }
        ];


    });


}


if ($('#health-chart').length > 0) {


    // Create chart instance
    var chart = am4core.create("health-chart", am4charts.XYChart);

// Add data
    chart.data = [{
        "year": "2010",
        "population": 501.9,
        "assault": 250,
        "rape": 199
    }, {
        "year": "2011",
        "population": 301.9,
        "assault": 222,
        "rape": 251
    }, {
        "year": "2012",
        "population": 201.1,
        "assault": 170,
        "rape": 199
    }, {
        "year": "2013",
        "population": 165.8,
        "assault": 122,
        "rape": 90
    }, {
        "year": "2014",
        "population": 139.9,
        "assault": 99,
        "rape": 252
    }, {
        "year": "2015",
        "population": 128.3,
        "assault": 85,
        "rape": 84
    }, {
        "year": "2016",
        "population": 99,
        "assault": 93,
        "rape": 142
    }];

// Create axes
    var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
    categoryAxis.dataFields.category = "year";
    categoryAxis.title.text = "Health Data";
    categoryAxis.renderer.grid.template.location = 0;
    categoryAxis.renderer.minGridDistance = 20;

    var  valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
    valueAxis.title.text = "Value ";

// Create series
    var series = chart.series.push(new am4charts.ColumnSeries());
    series.dataFields.valueY = "population";
    series.dataFields.categoryX = "year";
    series.name = "Populations";
    series.tooltipText = "{name}: [bold]{valueY}[/]";
// This has no effect
// series.stacked = true;

    var series2 = chart.series.push(new am4charts.ColumnSeries());
    series2.dataFields.valueY = "assault";
    series2.dataFields.categoryX = "year";
    series2.name = "Insurance Coverage";
    series2.tooltipText = "{name}: [bold]{valueY}[/]";
// Do not try to stack on top of previous series
// series2.stacked = true;

    var series3 = chart.series.push(new am4charts.ColumnSeries());
    series3.dataFields.valueY = "rape";
    series3.dataFields.categoryX = "year";
    series3.name = "Doctors";
    series3.tooltipText = "{name}: [bold]{valueY}[/]";
    series3.stacked = true;

// Add cursor
    chart.cursor = new am4charts.XYCursor();

// Add legend
    chart.legend = new am4charts.Legend();




}



