import * as am4core from "@amcharts/amcharts4/core";
import * as am4charts from "@amcharts/amcharts4/charts";
import am4themes_animated from "@amcharts/amcharts4/themes/animated";

am4core.useTheme(am4themes_animated);

if ($('#education-chart').length > 0) {


    // Create chart instance
    var chart = am4core.create("education-chart", am4charts.XYChart);

// Add data
    chart.data = [{
        "year": "2010",
        "graduate": 501.9,

    }, {
        "year": "2011",
        "graduate": 301.9,

    }, {
        "year": "2012",
        "graduate": 201.1,

    }, {
        "year": "2013",
        "graduate": 165.8,

    }, {
        "year": "2014",
        "graduate": 139.9,

    }, {
        "year": "2015",
        "graduate": 128.3,

    }, {
        "year": "2016",
        "graduate": 99,

    }];

// Create axes
    var categoryAxis = chart.yAxes.push(new am4charts.CategoryAxis());
    categoryAxis.dataFields.category = "year";
    categoryAxis.title.text = "College Graduate Data";
    categoryAxis.renderer.grid.template.location = 0;
    categoryAxis.renderer.minGridDistance = 20;

    var  valueAxis = chart.xAxes.push(new am4charts.ValueAxis());
    valueAxis.title.text = "Value ";

// Create series
    var series = chart.series.push(new am4charts.ColumnSeries());
    series.dataFields.valueX = "graduate";
    series.dataFields.categoryY = "year";
    series.name = "Women Graduates";
    series.tooltipText = "{name}: [bold]{valueY}[/]";
// This has no effect
// series.stacked = true;


// Add cursor
    chart.cursor = new am4charts.XYCursor();

// Add legend
    chart.legend = new am4charts.Legend();




}