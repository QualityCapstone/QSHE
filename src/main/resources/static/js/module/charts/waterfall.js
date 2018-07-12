import * as am4core from "@amcharts/amcharts4/core";
import * as am4charts from "@amcharts/amcharts4/charts";
import am4themes_animated from "@amcharts/amcharts4/themes/animated";
const api = require('../../lib/local');

am4core.useTheme(am4themes_animated);


let currentData = [];

api.getData("state/crime/TX").then(function(stateData) {

    console.log(stateData);
    let crimeData = stateData;

    for(let key in crimeData) {

        if(crimeData[key].year === 2015) {
            currentData = crimeData[key]
        }
    }

    console.log(currentData);



var chart = am4core.create("crime-chart", am4charts.XYChart);


chart.data = [ {
    "name": "Non-Violent",
    "open": 0,
    "close": currentData.nonViolentCrimeTotal,
    "balloonValue": currentData.nonViolentCrimeTotal,
    "config": {
        "fill": "#54cb6a"
    }
}, {
    "name": "Violent",
    "open": currentData.nonViolentCrimeTotal,
    "close": currentData.totalCrime ,
    "balloonValue": currentData.violentCrimeTotal,
    "config": {
        "fill": "#54cb6a"
    }
}, {
    "name": "Total Crime",
    "open": 0,
    "close": currentData.totalCrime,
    "balloonValue": currentData.totalCrime,
    "config": {
        "fill": "#169b2f"
    }


//    NON VIOLENT CRIMES

}, {
    "name": "Property Damage",
    "open": 0,
    "close": currentData.propertyCrimeCount,
    "balloonValue": currentData.propertyCrimeCount,
    "config": {
        "fill": "#cc4b48"
    }
}, {
    "name": "Burglary",
    "open": currentData.propertyCrimeCount,
    "close": currentData.propertyCrimeCount + currentData.burglaryCount,
    "balloonValue": currentData.burglaryCount,
    "config": {
        "fill": "#cc4b48"
    }
}, {
    "name": "Larceny",
    "open": currentData.propertyCrimeCount + currentData.burglaryCount,
    "close": currentData.propertyCrimeCount + currentData.burglaryCount + currentData.larcenyCount,
    "balloonValue": currentData.larcenyCount,
    "config": {
        "fill": "#1c8ceb"
    }
}, {
    "name": "Car Theft",
    "open": currentData.propertyCrimeCount + currentData.burglaryCount + currentData.larcenyCount,
    "close": currentData.propertyCrimeCount + currentData.burglaryCount + currentData.larcenyCount + currentData.motorTheftCount,
    "balloonValue": currentData.motorTheftCount,
    "config": {
        "fill": "#1c8ceb"
    }
}, {
    "name": "Assault",
    "open":  currentData.nonViolentCrimeTotal,
    "close":  currentData.nonViolentCrimeTotal + currentData.assaultCount,
    "balloonValue": currentData.assaultCount,
    "config": {
        "fill": "#1c8ceb"
    }

    //VIOLENT CRIMES
}, {
    "name": "Arson",
    "open":  currentData.propertyCrimeCount + currentData.burglaryCount + currentData.larcenyCount + currentData.motorTheftCount,
    "close":  currentData.propertyCrimeCount + currentData.burglaryCount + currentData.larcenyCount + currentData.motorTheftCount + currentData.arsonCount,
    "balloonValue": currentData.arsonCount,
    "config": {
        "fill": "#1c8ceb"
    }
}, {
    "name": "Arson",
    "open":  currentData.propertyCrimeCount + currentData.burglaryCount + currentData.larcenyCount + currentData.motorTheftCount,
    "close":  currentData.propertyCrimeCount + currentData.burglaryCount + currentData.larcenyCount + currentData.motorTheftCount + currentData.arsonCount,
    "balloonValue": currentData.arsonCount,
    "config": {
        "fill": "#1c8ceb"
    }
}, {
    "name": "Arson",
    "open":  currentData.propertyCrimeCount + currentData.burglaryCount + currentData.larcenyCount + currentData.motorTheftCount,
    "close":  currentData.propertyCrimeCount + currentData.burglaryCount + currentData.larcenyCount + currentData.motorTheftCount + currentData.arsonCount,
    "balloonValue": currentData.arsonCount,
    "config": {
        "fill": "#1c8ceb"
    }
}, {
    "name": "Arson",
    "open":  currentData.propertyCrimeCount + currentData.burglaryCount + currentData.larcenyCount + currentData.motorTheftCount,
    "close":  currentData.propertyCrimeCount + currentData.burglaryCount + currentData.larcenyCount + currentData.motorTheftCount + currentData.arsonCount,
    "balloonValue": currentData.arsonCount,
    "config": {
        "fill": "#1c8ceb"
    }
}


];







// Create axes
var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
categoryAxis.dataFields.category = "name";
categoryAxis.title.text = "Crime Category";


var  valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
valueAxis.title.text = "Incidents";
valueAxis.min = 500000;

// Create series
var series = chart.series.push(new am4charts.ColumnSeries());
series.dataFields.categoryX = "name";
series.dataFields.valueY = "close";
series.dataFields.openValueY = "open";


series.columns.template.configField = "config";
series.name = "Crime - 2010";
series.tooltipText = "{name}: [bold]{valueY}[/]";


// series.columns.template.adapter.add("fill", (fill, target) => {
//     return chart.colors.getIndex(target.dataItem.index);
// });

// Add cursor
chart.cursor = new am4charts.XYCursor();

// // Add legend
// chart.legend = new am4charts.Legend();


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


});