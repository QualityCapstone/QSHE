AmCharts.makeChart("map",{
    "type": "map",
    "pathToImages": "http://www.amcharts.com/lib/3/images/",
    "addClassNames": true,
    "fontSize": 15,
    "color": "#000000",
    "projection": "mercator",
    "backgroundAlpha": 1,
    "backgroundColor": "rgba(49,172,154,1)",
    "dataProvider": {
        "map": "usaTerritoriesLow",
        "getAreasFromMap": true,
        // "images": [
        //     {
        //         "top": 40,
        //         "left": 60,
        //         "width": 80,
        //         "height": 40,
        //         "pixelMapperLogo": true,
        //         "imageURL": "http://pixelmap.amcharts.com/static/img/logo-black.svg",
        //         "url": "http://www.amcharts.com"
        //     }
        // ],
        "areas": [
            {
                "id": "US-AK",
                "title": "Alaska Rank 39",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-AL",
                "title": "Alabama ranking 47",
                "color": "rgba(189,107,75,1)"
            },
            {
                "id": "US-AR",
                "title": "Arkansas Rank 50",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-AZ",
                "title": "Arizona Rank 36",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-CA",
                "title": "California Rank 19",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-CO",
                "title": "Colorado Rank 20",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-CT",
                "title": "Connecticut Rank 8",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-DE",
                "title": "Delaware Rank 12",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-FL",
                "title": "Florida Rank 31",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-GA",
                "title": "Georgia Rank 43",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-HI",
                "title": "Hawaii Rank 7",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-IA",
                "title": "Iowa Rank 9",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-ID",
                "title": "Idaho Rank 41",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-IL",
                "title": "Illinois Rank 11",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-IN",
                "title": "Indiana Rank 23",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-KS",
                "title": "Kansas Rank 28",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-KY",
                "title": "Kentucky Rank 34",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-LA",
                "title": "Louisiana Rank 51",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-MA",
                "title": "Massachusetts Rank 2",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-MD",
                "title": "Maryland Rank 22",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-ME",
                "title": "Maine Rank 6",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-MI",
                "title": "Michigan Rank 32",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-MN",
                "title": "Minnesota Rank 1",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-MO",
                "title": "Missouri Rank 37",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-MS",
                "title": "Mississippi Rank 49",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-MT",
                "title": "Montana Rank 26",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-NC",
                "title": "North Carolina Rank 30",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-ND",
                "title": "North Dakota Rank 4",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-NE",
                "title": "Nebraska Rank 17",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-NH",
                "title": "New Hampshire Rank 10",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-NJ",
                "title": "New Jersey Rank 16",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-NM",
                "title": "New Mexico Rank 40",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-NV",
                "title": "Nevada Rank 44",
                "color": "rgba(189,107,75,1)"
            },
            {
                "id": "US-NY",
                "title": "New York Rank 13",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-OH",
                "title": "Ohio Rank 29",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-OK",
                "title": "Oklahoma Rank 48",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-OR",
                "title": "Oregon Rank 18",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-PA",
                "title": "Pennsylvania Rank 35",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-RI",
                "title": "Rhode Island Rank 21",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-SC",
                "title": "South Carolina Rank 46",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-SD",
                "title": "South Dakota Rank 24",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-TX",
                "title": "Texas Rank 42",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-UT",
                "title": "Utah Rank 25",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-VA",
                "title": "Virginia Rank 27",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-VT",
                "title": "Vermont Rank 3",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-WA",
                "title": "Washington Rank 14",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-WI",
                "title": "Wisconsin Rank 5",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-WV",
                "title": "West Virginia Rank 35",
                "color": "rgba(186,99,64,1)"
            },
            {
                "id": "US-WY",
                "title": "Wyoming Rank 33",
                "color": "rgba(186,99,64,1)"
            }
        ],
        "lines": [
            {
                "selectable": true,
                "color": "rgba(168,60,170,0.8)",
                "longitudes": [
                    -122.4903,
                    -122.6586
                ],
                "latitudes": [
                    40.453,
                    40.3672
                ],
                "arrow": "none"
            }
        ]
    },
    "balloon": {
        "horizontalPadding": 15,
        "borderAlpha": 0,
        "borderThickness": 1,
        "verticalPadding": 15
    },
    "areasSettings": {
        "color": "rgba(186,99,64,1)",
        "outlineColor": "rgba(49,172,154,1)",
        "rollOverOutlineColor": "rgba(49,172,154,1)",
        "rollOverBrightness": 20,
        "selectedBrightness": 20,
        "selectable": true,
        "unlistedAreasAlpha": 0,
        "unlistedAreasOutlineAlpha": 0
    },
    "imagesSettings": {
        "alpha": 1,
        "color": "rgba(186,99,64,1)",
        "outlineAlpha": 0,
        "rollOverOutlineAlpha": 0,
        "outlineColor": "rgba(49,172,154,1)",
        "rollOverBrightness": 20,
        "selectedBrightness": 20,
        "selectable": true
    },
    "linesSettings": {
        "color": "rgba(186,99,64,1)",
        "selectable": true,
        "rollOverBrightness": 20,
        "selectedBrightness": 20
    },
    "zoomControl": {
        "zoomControlEnabled": true,
        "homeButtonEnabled": false,
        "panControlEnabled": false,
        "right": 38,
        "bottom": 30,
        "minZoomLevel": 0.25,
        "gridHeight": 100,
        "gridAlpha": 0.1,
        "gridBackgroundAlpha": 0,
        "gridColor": "#FFFFFF",
        "draggerAlpha": 1,
        "buttonCornerRadius": 2
    }
});