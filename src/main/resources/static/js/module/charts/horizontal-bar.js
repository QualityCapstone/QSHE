import * as am4core from "@amcharts/amcharts4/core";
import * as am4charts from "@amcharts/amcharts4/charts";
import am4themes_animated from "@amcharts/amcharts4/themes/animated";
const api = require('../../lib/local');


let abbr = $('#state-name').attr('data-abbr');

api.getData("state/graduates/" + abbr).then(function(fetchData) {

    console.log(fetchData);
    let  gradData = fetchData;


    am4core.useTheme(am4themes_animated);

    if ($('#education-chart').length > 0) {

        // Create chart instance
        var chart = am4core.create("education-chart", am4charts.XYChart);


// Add data
        chart.data = gradData;

// Create axes
        var categoryAxis = chart.yAxes.push(new am4charts.CategoryAxis());
        categoryAxis.dataFields.category = "year";
        categoryAxis.title.text = "College Graduate Data";

        // categoryAxis.renderer.grid.template.location = 0;
        // categoryAxis.renderer.minGridDistance = 20;


        var valueAxis = chart.xAxes.push(new am4charts.ValueAxis());
        valueAxis.title.text = "Graduates";
        valueAxis.strictMinMax = true;
        valueAxis.min = 0;
        // valueAxis.autoGridCount = false;
        // valueAxis.gridCount = 50;
        // valueAxis.labelFrequency = 10;

// Create series
        var series = chart.series.push(new am4charts.ColumnSeries());
        series.dataFields.valueX = "graduateCount";
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

});