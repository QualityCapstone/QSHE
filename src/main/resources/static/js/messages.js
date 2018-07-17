const animate = require("animate.css");

$.fn.extend({
    animateCss: function(animationName, callback) {
        var animationEnd = (function(el) {
            var animations = {
                animation: 'animationend',
                OAnimation: 'oAnimationEnd',
                MozAnimation: 'mozAnimationEnd',
                WebkitAnimation: 'webkitAnimationEnd',
            };

            for (var t in animations) {
                if (el.style[t] !== undefined) {
                    return animations[t];
                }
            }
        })(document.createElement('div'));

        this.addClass('animated ' + animationName).one(animationEnd, function() {
            $(this).removeClass('animated ' + animationName);

            if (typeof callback === 'function') callback();
        });

        return this;
    },
});


$('#topic-submit').hide();
$('#title').css("width","450px");
$('#title').hide();



$('#topic-new').click(function(e){
    e.preventDefault();

    $('#topic-submit').show();
    $('#title').show();


    $('#topic-submit').addClass('animated bounceInRight');
    $('#title').addClass('animated bounceInRight');

});