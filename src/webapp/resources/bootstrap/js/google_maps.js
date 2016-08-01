$(document).ready(function () {
    var companyPos = new google.maps.LatLng(50.38746334, 30.47396131);
    var companyLogo = new google.maps.MarkerImage('/resources/img/marker.png', new google.maps.Size(100, 100),
        new google.maps.Point(0, 0),
        new google.maps.Point(50, 00)
    );
    var settings = {
        zoom: 15,
        center: companyPos,
        mapTypeControl: true,
        mapTypeControlOptions: {style: google.maps.MapTypeControlStyle.DROPDOWN_MENU},
        navigationControl: true,
        navigationControlOptions: {style: google.maps.NavigationControlStyle.SMALL},
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    var map = new google.maps.Map(document.getElementById("google-map"), settings);

    var companyMarker = new google.maps.Marker({
        icon: companyLogo,
        position: companyPos,
        map: map,
        title: "Alex Coffee || Лучший магазин кофе и чая",
    });
    var width = $('#google-map-block').css('width');
    $('#google-map').css('width', width);
    $('#google-map').css('height', '400px');

    $(window).resize(function () {
        var width = $('#google-map-block').css('width');
        $('#google-map').css('width', width);
    });

});