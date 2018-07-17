import css from '../css/map.css';
 const NewsAPI = require('newsapi');
 const newsapi = new NewsAPI('b8cc911c5a604eadac02a424f0945ae8');

    // To query /v2/everything
// You must include at least one q, source, or domain

    let query = $('#statenews').attr("data-state-name") + ' female';

    // functions to pull current date
    let today = new Date();
    let dd = today.getDate();
    let mm = today.getMonth()+1; //January is 0!
    let mm1 = today.getMonth();
    let yyyy = today.getFullYear();
    let monthago;

    if(dd<10) {
        dd = '0'+dd
    }

    if(mm<10) {
        mm = '0'+mm
    }

    today = yyyy + '-' + mm + '-' + dd;
    monthago = yyyy + '-' + mm1 + '-' + dd;


    newsapi.v2.everything({

        q: query, //need help changing state name for each state.

        sources: 'bbc-news,the-verge,abc-news,cbs-news,cnbc,cnn,fox-news',
        from: today,
        to: monthago,
        language: 'en',
        sortBy: 'relevancy'
    }).then(response => {

        for (let i = 0; i <=2; i++) {
            $('#statenews').append(
                `<li class='text-truncate'>
                    <a href="${response.articles[i].url}">
                        ${response.articles[i].title}
                    </a>                 
                </li>
                <li class="text-truncate pb-2">
                    <span class=""> ${response.articles[i].description}</span>
                </li>`
            );
        }
        for (let i = 0; i < response.articles.length ; i++) {



            $('#morenews').append(
                `<li>
                    <a href="${response.articles[i].url}">
                        <h4 class="text-center">${response.articles[i].title}</h4>
                    </a>
                </li>
                <li>
                    <span>${response.articles[i].description}</span>
                </li>
                <br />`
            )
        }
    });
