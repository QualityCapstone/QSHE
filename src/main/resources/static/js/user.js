


// profile scrolling add class {
$(window).scroll(function (event) {
    var scroll = $(window).scrollTop();
    // Do something

    console.log(scroll);

    if(scroll > 56) {
        console.log("big");
          $('#profile-nav').addClass("profile-nav");
    }
    if(scroll <= 56) {
        console.log("smal")
        $('#profile-nav').removeClass("profile-nav");
    }

});


