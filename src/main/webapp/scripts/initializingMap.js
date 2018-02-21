/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function initMap() {
    var mapOptions = {
        center: new google.maps.LatLng(54.4131222, 18.5343133),
        zoom: 18
    }
    var map = new google.maps.Map(document.getElementById("map"), mapOptions);
}