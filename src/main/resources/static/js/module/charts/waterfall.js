import * as am4core from "@amcharts/amcharts4/core";
import * as am4charts from "@amcharts/amcharts4/charts";
import am4themes_animated from "@amcharts/amcharts4/themes/animated";

am4core.useTheme(am4themes_animated);


var chart = am4core.create("health-chart", am4charts.XYChart);

chart.data = [ {
    "name": "Income A",
    "open": 0,
    "close": 11.13,
    "color": "#54cb6a",
    "balloonValue": 11.13
}, {
    "name": "Income B",
    "open": 11.13,
    "close": 15.81,
    "color": "#54cb6a",
    "balloonValue": 4.68
}, {
    "name": "Total Income",
    "open": 0,
    "close": 15.81,
    "color": "#169b2f",
    "balloonValue": 15.81
}, {
    "name": "Expenses A",
    "open": 12.92,
    "close": 15.81,
    "color": "#cc4b48",
    "balloonValue": 2.89
}, {
    "name": "Expenses B",
    "open": 8.64,
    "close": 12.92,
    "color": "#cc4b48",
    "balloonValue": 4.24
}, {
    "name": "Revenue",
    "open": 0,
    "close": 8.64,
    "color": "#1c8ceb",
    "balloonValue": 11.13
} ];



// Create axes
var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
categoryAxis.dataFields.category = "name";
categoryAxis.title.text = "Crime Category";
categoryAxis.renderer.grid.template.location = 0;
categoryAxis.renderer.minGridDistance = 20;

var  valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
valueAxis.title.text = "Incidents";


// Create series
var series = chart.series.push(new am4charts.ColumnSeries());
series.dataFields.valueY = "balloonValue";
series.dataFields.categoryX = "close";
series.dataFields.openCategoryX = "open";
series.name = "Crime - 2010";
series.tooltipText = "{name}: [bold]{valueY}[/]";
// This has no effect
// series.stacked = true;


// Add cursor
chart.cursor = new am4charts.XYCursor();

// Add legend
chart.legend = new am4charts.Legend();


//     "valueAxes": [ {
//     "axisAlpha": 0,
//     "gridAlpha": 0.1,
//     "position": "left"
// } ],
//     "startDuration": 1,
//     "graphs": [ {
//     "balloonText": "<span style='color:[[color]]'>[[category]]</span><br><b>$[[balloonValue]] Mln</b>",
//     "colorField": "color",
//     "fillAlphas": 0.8,
//     "labelText": "$[[balloonValue]]",
//     "lineColor": "#BBBBBB",
//     "openField": "open",
//     "type": "column",
//     "valueField": "close"
// } ],
//     "trendLines": [ {
//     "dashLength": 3,
//     "finalCategory": "Income B",
//     "finalValue": 11.13,
//     "initialCategory": "Income A",
//     "initialValue": 11.13,
//     "lineColor": "#888888"
// }, {
//     "dashLength": 3,
//     "finalCategory": "Expenses A",
//     "finalValue": 15.81,
//     "initialCategory": "Income B",
//     "initialValue": 15.81,
//     "lineColor": "#888888"
// }, {
//     "dashLength": 3,
//     "finalCategory": "Expenses B",
//     "finalValue": 12.92,
//     "initialCategory": "Expenses A",
//     "initialValue": 12.92,
//     "lineColor": "#888888"
// }, {
//     "dashLength": 3,
//     "finalCategory": "Revenue",
//     "finalValue": 8.64,
//     "initialCategory": "Expenses B",
//     "initialValue": 8.64,
//     "lineColor": "#888888"
// } ],
//     "columnWidth": 0.6,
//     "categoryField": "name",
//     "categoryAxis": {
//     "gridPosition": "start",
//         "axisAlpha": 0,
//         "gridAlpha": 0.1
// },
// "export": {
//     "enabled": true
// }
// } );