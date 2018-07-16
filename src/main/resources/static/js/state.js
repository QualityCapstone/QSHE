import css from '../css/map.css';


const api = require('./lib/local');


const radarChart = require('./module/charts/radar');
const horizontalBar = require('./module/charts/horizontal-bar');
const growthChart = require('./module/charts/growth');
const healthChart = require('./module/charts/waterfall');

require('jquery-scrollify')($);


window.onload = function () {


    if ($('#inputState').length > 0) {

        $('#inputState').on('change', function() {

            let currentAbbr = $('#state-tiny').attr("data-state-abbr");
            let abbr =  this.value;

            $('.state-btn').attr('data-selected-abbr',abbr);

        });


        $('.state-btn').click(function() {

            let selected = $(this).attr('data-selected-abbr');
            let current = $(this).attr('data-current-abbr');


            console.log(selected);
            console.log(current);


            if (current === undefined) {
                console.log("this is go to button");
                window.location = "/state/" + selected.toUpperCase();
            } else {

                if(selected === undefined) {
                    console.log("nothing is selected");
                    return;
                }

                window.location = "/state/compare/" + current.toUpperCase() + "/" + selected.toUpperCase() ;


            }

        });


    }

    $.scrollify({
        section : ".page",
        interstitialSection : "body",
        easing: "easeOutExpo",
        scrollSpeed: 1100,
        offset : -62,
        scrollbars: true,
        standardScrollElements: "",
        overflowScroll: true,
        updateHash: true,
        touchScroll:true,
        before:function() {},
        after:function() {},
        afterResize:function() {},
        afterRender:function() {}
    });




}