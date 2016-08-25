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

    $(document.body).on('appear', '#label-main', function (e, $affected) {
        $('#label-main').addClass('animated zoomIn');
    });
    $('#label-main').appear({force_process: true});

    $(document.body).on('appear', '#label-products', function (e, $affected) {
        $('#label-products').addClass('animated zoomIn');
    });
    $('#label-products').appear({force_process: true});

    $(document.body).on('appear', '#label-category', function (e, $affected) {
        $('#label-category').addClass('animated zoomIn');
    });
    $('#label-category').appear({force_process: true});

    $(document.body).on('appear', '#label-delivery', function (e, $affected) {
        $('#label-delivery').addClass('animated zoomIn');
    });
    $('#label-delivery').appear({force_process: true});

    $(document.body).on('appear', '#label-payments', function (e, $affected) {
        $('#label-payments').addClass('animated zoomIn');
    });
    $('#label-payments').appear({force_process: true});

    $(document.body).on('appear', '#label-cantacts', function (e, $affected) {
        $('#label-cantacts').addClass('animated zoomIn');
    });
    $('#label-cantacts').appear({force_process: true});

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
});
