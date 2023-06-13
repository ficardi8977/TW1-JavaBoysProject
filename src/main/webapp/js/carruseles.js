$(document).ready(function(){
    var slidesToShow = 5;
    var slidesToScroll = 3;

    if (window.matchMedia("(max-width: 767px)").matches) {
        // Dispositivo móvil
        slidesToShow = 2;
        slidesToScroll = 1;
    }

    $('.items').slick({
        infinite: true,
        slidesToShow: slidesToShow,
        slidesToScroll: slidesToScroll
    });
});