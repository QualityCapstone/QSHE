#Q-SHE
-
Codeup Capstone Project

A Web app that allows registered users to rate States based on crime, employment opportunity, education, healthcare, and growth. Users can compare their own ratings and experiences with other users through messaging and blogging.

#Features
--

* Login 
    * Create page that redirects to profile page
    * Pull API’s to allow user to login with Google or Facebook account
    * Register
    * Create page that redirects to login
    * Allow user to register logging into user Google or Facebook accounts
    * Prompt user to add residence/living State. 
    * Ask user to rate experiences on 5 indexes from 1 to 10 may respond to the rating at this point or do it later.

* Map page
    * Download/Install Interactive Map that shows the general rating on each element with a popup. For logged in users when click you go to State page.
    * 1.1 Install/Download Amchart map. https://www.amcharts.com/javascript-maps/ 
    * 1.2 API’s  to pull data:
        * Violence: /api/nibrs/{offense}/offender/states/{stateAbbr}/{sex}
		    * https://crime-data-explorer.fr.cloud.gov/api#/
        * Education: Graduates/locationID/grads_women
            * https://github.com/DataUSA/datausa-api/wiki/Attribute-API 
        * Growth: yg_poverty/locationID/poverty_female
            * https://github.com/DataUSA/datausa-api/wiki/Attribute-API 
        * Employment: 
            * https://www.bls.gov/developers/
        * Health: 
            * Not API’s found yet we may use data from Data USA in the categories of: 
Teen Birth, Female medicare enrolled or we can get the information from: 
https://statusofwomendata.org/explore-the-data/download-the-data/  
Not available in JSON. 
        * 1.3 Customize map  to show the information of the rating pull from the API’s or Data  and create links to redirect to State page.
        * 1.4 Append html to show top 5 states, bottom 5 states.
* Profile page
    * Create page that allow user to add,  edit data  or delete profile.
    * Allow user to add other users and append them in this page.
    * Allow user to create and send messages to other users. (new page? Or in same page?)
    * Show/Display user ratings  and redirect to page that allows user to edit rate 
    * Show/Display notifications of messages  that link to message pages
    * Create page to allow user to send messages to the users added
*  News Notification System 
    * News from data pull from  API: https://newsapi.org/docs/endpoints/top-headlines
    * Create page with all the news and trends on the User’s State or just a widget of news in profile page?
    * Create link (with state image) that redirects to that State page
    * Create link (with US image) that redirects to Map (landing page)
* State page
    * Image of State
    * Pull and display general ranking from API’s of this state
    * Pull and display general ranking from User’s reviews/rates
    * Use Javascript Charts to display the Radar chart with the rate information of the 5 indexes.
        * https://www.amcharts.com/javascript-charts/ 
    * Widget with news/trends of that stage with link to the News Page
    * Widget showing posts with tags from that State.
    * We want list of indexes displayed with graphic on that page or is enough the radar chart?







#Setup


#Technologies
* Java Web Application
* Spring Framework 
* HTML / CSS
* RestAPI integration 


#Team members 
Brian, Dane, Monica, Richard

