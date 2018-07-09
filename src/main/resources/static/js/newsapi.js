    const $ = require('jquery');
    const NewsAPI = require('newsapi');
    const newsapi = new NewsAPI('b8cc911c5a604eadac02a424f0945ae8');
// To query /v2/top-headlines
// All options passed to topHeadlines are optional, but you need to include at least one of them
    newsapi.v2.topHeadlines({
        // sources: 'bbc-news,the-verge',
        // q: 'Texas',
        // category: 'business',
        language: 'en',
        country: 'us'
    }).then(response => {
        // console.log(response);
    });

// To query /v2/everything
// You must include at least one q, source, or domain
    newsapi.v2.everything({
        q: 'texas',

        // need help changing this q to the name of each
        // state based on which state the user selects

        sources: 'bbc-news,the-verge,abc-news,cbs-news,cnbc,cnn,fox-news',
        // domains: 'bbc.co.uk, techcrunch.com',
        from: '2018-06-18',
        to: '2018-07-17',
        language: 'en',
        sortBy: 'relevancy'
    }).then(response => {
        console.log(response);
        console.log(response.articles);
        console.log(response.articles[0]);
        console.log(response.articles[0].url);
        /*
          {
            status: "ok",
            articles: [...]
          }
        */
        $('.state').append(
            "<div class ='card-body'><p>" +
            response.articles[0].url +
            "</p></div>"
        )
    });

// To query sources
// All options are optional
    newsapi.v2.sources({
        language: 'en',
        country: 'us'
    }).then(response => {
        // console.log(response);
    });