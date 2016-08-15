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
    $(document.body).on('appear', '.main', function (e, $affected) {
        /*$('#nav-main').addClass('active');
        $('#nav-categories').removeClass('active');
        $('#nav-all-products').removeClass('active');
        $('#nav-delivery').removeClass('active');
        $('#nav-payments').removeClass('active');
        $('#nav-contacts').removeClass('active');*/
        $(".label-main").addClass('animated zoomIn');
    });
    $('.main').appear({force_process: true});
    $(document.body).on('appear', '.categories', function (e, $affected) {
        /*$('#nav-main').removeClass('active');
        $('#nav-categories').addClass('active');
        $('#nav-all-products').removeClass('active');
        $('#nav-delivery').removeClass('active');
        $('#nav-payments').removeClass('active');
        $('#nav-contacts').removeClass('active');*/
        $(".label-categories").addClass('animated zoomIn');
    });
    $('.categories').appear({force_process: true});
    $(document.body).on('appear', '.all-products', function (e, $affected) {
        /*$('#nav-main').removeClass('active');
        $('#nav-categories').removeClass('active');
        $('#nav-all-products').addClass('active');
        $('#nav-delivery').removeClass('active');
        $('#nav-payments').removeClass('active');
        $('#nav-contacts').removeClass('active');*/
        $(".label-all-products").addClass('animated zoomIn');
    });
    $('.all-products').appear({force_process: true});
    $(document.body).on('appear', '.delivery', function (e, $affected) {
        /*$('#nav-main').removeClass('active');
        $('#nav-categories').removeClass('active');
        $('#nav-all-products').removeClass('active');
        $('#nav-delivery').addClass('active');
        $('#nav-payments').removeClass('active');
        $('#nav-contacts').removeClass('active');*/
        $(".label-delivery").addClass('animated zoomIn');
    });
    $('.delivery').appear({force_process: true});
    $(document.body).on('appear', '.payments', function (e, $affected) {
        /*$('#nav-main').removeClass('active');
        $('#nav-categories').removeClass('active');
        $('#nav-all-products').removeClass('active');
        $('#nav-delivery').removeClass('active');
        $('#nav-payments').addClass('active');
        $('#nav-contacts').removeClass('active');*/
        $(".label-payments").addClass('animated zoomIn');
    });
    $('.payments').appear({force_process: true});
    $(document.body).on('appear', '.google-map', function (e, $affected) {
        /*$('#nav-main').removeClass('active');
        $('#nav-categories').removeClass('active');
        $('#nav-all-products').removeClass('active');
        $('#nav-delivery').removeClass('active');
        $('#nav-payments').removeClass('active');
        $('#nav-contacts').addClass('active');*/
        $(".label-contacts").addClass('animated zoomIn');
    });
    $('.google-map').appear({force_process: true});

    $(document.body).on('appear', '#label-all-products', function (e, $affected) {
        $('#label-all-products').addClass('animated zoomIn');
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
