$(document).ready(function () {
    $("#menu").on("click", "a", function (event) {
        event.preventDefault();
        var id = $(this).attr('href'),
            top = $(id).offset().top;
        $('body,html').animate({scrollTop: top}, 1500);
    });
    
    $("#menu_bottom").on("click", "a", function (event) {
        event.preventDefault();
        var id = $(this).attr('href'),
            top = $(id).offset().top;
        $('body,html').animate({scrollTop: top}, 1500);
    });

    $('.main').appear({force_process: true});
    $('.categories').appear({force_process: true});
    $('.products').appear({force_process: true});
    $('.delivery').appear({force_process: true});
    $('.payments').appear({force_process: true});
    $('.contacts').appear({force_process: true});

    $(document.body).on('appear', '#label-products', function (e, $affected) {
        $('#label-products').addClass('animated zoomIn');
    });
    $(document.body).on('appear', '#icon1', function (e, $affected) {
        $('#icon1').addClass('animated bounce');
    });
    $('#icon1').appear({force_process: true});

    $(document.body).on('appear', '#icon2', function (e, $affected) {
        $('#icon2').addClass('animated bounce');
    });
    $('#icon2').appear({force_process: true});

    $(document.body).on('appear', '#icon3', function (e, $affected) {
        $('#icon3').addClass('animated bounce');
    });
    $('#icon3').appear({force_process: true});

    $(document.body).on('appear', '#icon4', function (e, $affected) {
        $('#icon4').addClass('animated bounce');
    });
    $('#icon4').appear({force_process: true});

    $(document.body).on('appear', '#icon5', function (e, $affected) {
        $('#icon5').addClass('animated bounce');
    });
    $('#icon5').appear({force_process: true});

    $(document.body).on('appear', '#icon6', function (e, $affected) {
        $('#icon6').addClass('animated bounce');
    });
    $('#icon6').appear({force_process: true});

    $(document.body).on('appear', '#icon7', function (e, $affected) {
        $('#icon7').addClass('animated bounce');
    });
    $('#icon7').appear({force_process: true});

    $(document.body).on('appear', '#icon8', function (e, $affected) {
        $('#icon8').addClass('animated bounce');
    });
    $('#icon8').appear({force_process: true});

    $(document.body).on('appear', '#icon9', function (e, $affected) {
        $('#icon9').addClass('animated bounce');
    });
    $('#icon9').appear({force_process: true});
});
