let map;
let marker1, marker2;
let geocoder;

function iniciarMap() {
    //ponemos las cordenadas donde vamos a ver el mapa
    map = new google.maps.Map(document.getElementById('map'), {
        center: { lat: 4.713292163053211, lng:-74.0652548199008 },
        zoom: 12
    });
    //ponemos las cordenadas donde vamos a ver el primer ubicador (la bola roja) apenas se muestra el mapa
    marker1 = new google.maps.Marker({
        position: { lat: 4.713292163053211, lng:-74.0652548199008 },
        map: map,
        draggable: true,
        label: "A"
    });
    //ponemos las cordenadas donde vamos a ver el segundo ubicador (la bola roja) apenas se muestra el mapa
    marker2 = new google.maps.Marker({
        position: { lat: 4.7064488942387115, lng:-74.05186523249866 },
        map: map,
        draggable: true,
        label: "B"
    });

    geocoder = new google.maps.Geocoder();
    //dragened es para cuando movemos la bola roja en el mapa (eso dispara el evento)
    google.maps.event.addListener(marker1, 'dragend', function(event) {
        document.getElementById('lat1').value = event.latLng.lat();
        document.getElementById('lng1').value = event.latLng.lng();
    });

    google.maps.event.addListener(marker2, 'dragend', function(event) {
        document.getElementById('lat2').value = event.latLng.lat();
        document.getElementById('lng2').value = event.latLng.lng();
    });
}

function geocodeAddress(marker, latField, lngField, addressField) {
    const address = document.getElementById(addressField).value;
    geocoder.geocode({ 'address': address }, function(results, status) {
        if (status === 'OK') {
            map.setCenter(results[0].geometry.location);
            marker.setPosition(results[0].geometry.location);
            document.getElementById(latField).value = results[0].geometry.location.lat();
            document.getElementById(lngField).value = results[0].geometry.location.lng();
        } else {
            alert('Geocodificación fallida debido a: ' + status);
        }
    });
}

function geocodeAddress1() {
    geocodeAddress(marker1, 'lat1', 'lng1', 'direccionR');
}

function geocodeAddress2() {
    geocodeAddress(marker2, 'lat2', 'lng2', 'direccionT');
}