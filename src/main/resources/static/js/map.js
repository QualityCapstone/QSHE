import css from '../css/map.css';



const data = require('./module/map/us-map-svg');
const Raphael = require('raphael');
const Popper = require('popper.js').default;
const Tooltip = require('tooltip.js').default;

const api = require('./lib/local');

let apiData;

api.getData("state/ratings/average").then(function(data) {
    console.log(data);
    apiData = data;
});


window.onload = function () {

    if ($('#map').length > 0) {
        require('../css/map-world.css');

        console.log("map found");



        // exists.
        var R = Raphael("map", "100%", "100%"),
            attr = {
                "fill": "#ff9890",
                "stroke": "#fff",
                "stroke-opacity": "3",
                "stroke-linejoin": "round",
                "stroke-miterlimit": "10",
                "stroke-width": "3.5",
                "stroke-dasharray": "none"
            },
            usRaphael = {};

        //Draw Map and store Raphael paths
        for (var state in data) {
            usRaphael[state] = R.path(data[state]).attr(attr);
        }

        //Do Work on Map
        for (var state in usRaphael) {
            usRaphael[state].color = Raphael.getColor();

            usRaphael[state].attr({"fill": usRaphael[state].color});

            (function (st, state) {


                st[0].style.cursor = "pointer";

                let popEle = $('#popperElement');
                popEle.hide();

                st[0].onmouseover = function () {
                    st.animate({fill: "#ff9890"}, 500);
                    st.toFront();
                    popperUpdate(state);
                    popEle.show();

                    const instance = new Popper(st[0], popEle, {
                        placement: 'auto',
                        title: state,
                        trigger: 'hover focus',
                        delay: {
                            show: 500, hide: 500
                        },
                        boundariesElement: '#map',
                        html: true,
                    });

                    instance.scheduleUpdate();
                };
                st[0].onmouseout = function () {
                    st.animate({fill: st.color}, 500);
                    st.toFront();

                    popEle.hide();


                };
                st[0].onclick = function () {
                    window.location = "/state/" + state.toUpperCase();
                }

            })(usRaphael[state], state);
        }

        function popperUpdate(inputState) {
            let popEle = $('#popperElement');

           let selectedState = [];
           let selectedMetrics = [];

            for(state in apiData) {

               let currState = apiData[state].abbr;

                if  (currState === inputState.toUpperCase()) {
                    selectedState =  apiData[state];
                    selectedMetrics = apiData[state].metrics;
                }
            }

            $('#state-name').text(selectedState.name);
            $('#state-abbr').text(selectedState.abbr);


            for(let key in  selectedMetrics) {

                let percent = selectedMetrics[key] * 10;

                $(`#${key.toLowerCase()}-progress`).css("width",percent + '%');

                console.log(key + selectedMetrics[key]);

            }

            console.log(selectedMetrics);


        }


    } // end of full map




};



