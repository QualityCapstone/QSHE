import * as am4core from "@amcharts/amcharts4/core";
import * as am4charts from "@amcharts/amcharts4/charts";
import am4themes_animated from "@amcharts/amcharts4/themes/animated";
const api = require('../../lib/local');

am4core.useTheme(am4themes_animated);

let abbr = $('#state-name').attr('data-abbr');

api.getData("state/population/" + abbr).then(function(stateData) {

    console.log(stateData);
    let popData = stateData;




var chart = am4core.create("growth-chart", am4charts.XYChart);
chart.paddingRight = 20;

var data = [];
var open = 100;
var close = 250;


for (let key in popData) {
    open = popData[key].population;
    close =  popData[key].population;

    data.push({ date: new Date(popData[key].dateDataCreated), open: open, close: close });
}

chart.data = data;

var dateAxis = chart.xAxes.push(new am4charts.DateAxis());

var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
valueAxis.tooltip.disabled = true;

var series = chart.series.push(new am4charts.LineSeries());
series.dataFields.dateX = "date";
series.dataFields.openValueY = "open";
series.dataFields.valueY = "close";
series.tooltipText = "Population: {openValueY.value}";
series.sequencedInterpolation = true;
series.fillOpacity = 0.3;
series.defaultState.transitionDuration = 1000;
series.tensionX = 0.8;

var series2 = chart.series.push(new am4charts.LineSeries());
series2.dataFields.dateX = "date";
series2.dataFields.valueY = "open";
series2.sequencedInterpolation = true;
series2.defaultState.transitionDuration = 1500;
series2.stroke = chart.colors.getIndex(6);
series2.tensionX = 0.8;

chart.cursor = new am4charts.XYCursor();
chart.cursor.xAxis = dateAxis;
chart.scrollbarX = new am4core.Scrollbar();


});
