function initMap() {

    var map = new google.maps.Map(document.getElementById('ieatmaps'), {
      center: {lat: 34.0937458, lng: -118.3614978},
      zoom: 12,
      styles: [
    {
        "featureType": "all",
        "stylers": [
            {
                "saturation": 0
            },
            {
                "hue": "#e7ecf0"
            }
        ]
    },
    {
        "featureType": "road",
        "stylers": [
            {
                "saturation": -70
            }
        ]
    },
    {
        "featureType": "transit",
        "stylers": [
            {
                "visibility": "off"
            }
        ]
    },
    {
        "featureType": "poi",
        "stylers": [
            {
                "visibility": "off"
            }
        ]
    },
    {
        "featureType": "water",
        "stylers": [
            {
                "visibility": "simplified"
            },
            {
                "saturation": -60
            }
        ]
    }
]
    });

    var marker = new google.maps.Marker({
        position: new google.maps.LatLng(34.0937458, -118.3614978)
        , title: 'ASL'
        , map : map

    });
  }
