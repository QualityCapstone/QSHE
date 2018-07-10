    const $ = require('jquery');
    const NewsAPI = require('newsapi');
    const newsapi = new NewsAPI('b8cc911c5a604eadac02a424f0945ae8');
// To query /v2/everything
// You must include at least one q, source, or domain
    newsapi.v2.everything({
        q: 'oregon', //need help changing state name for each state.
        sources: 'bbc-news,the-verge,abc-news,cbs-news,cnbc,cnn,fox-news',
        from: '2018-06-18',
        to: '2018-07-17',
        language: 'en',
        sortBy: 'relevancy'
    }).then(response => {
        // console.log(response);
        // console.log(response.articles);
        // console.log(response.articles[0]);
        // console.log(response.articles[0].url);
        for (let i = 0; i <=2; i++) {
            $('#statenews').append(
                "<li><a href='" + response.articles[i].url + "'>"
                + response.articles[i].title + ":</a>" + " " +
                response.articles[i].description + "</li>"
            );
        }
    });
