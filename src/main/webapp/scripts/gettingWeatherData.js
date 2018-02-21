/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var openWeatherAppId = 'f55d3f92ce6696f2a094b0b2a7f99e6f',
    openWeatherUrl = 'http://api.openweathermap.org/data/2.5/forecast',
    lat = 54.4131222, // współrzędne geograficzne zoo
    lon = 18.5343133;

$(document).ready(function(){
    $('.metric').click(function() {
            getData('metric');
    });
    $('.default').click(function() {
            getData('');
    });
});

function getData (units) {
    $.getJSON(openWeatherUrl, {lat: lat, lon: lon, appid: openWeatherAppId, units: units, cnt: document.getElementById("range").value*8}, function(forecast) {          
    var html = '';
        html += '<table style="width:100%" > <thead> <tr>' + '<th>' 
                + "Data i czas" + '</th> <th>' 
                + "   " + '</th> <th>' 
                + "Temperatura (°C/K)" + '</th> <th>' 
                + "Wilgotność (%)" + '</th> <th>' 
                + "Zachmurzenie (%)" + '</th> <th>' 
                + "Ciśnienie (hPa)" + '</th> </tr> </thead> <tbody>';
        forecast.list.forEach(function(forecastEntry, index, list){
            var icon = JSON.stringify(forecastEntry.weather[0].icon);
            icon = icon.replace("\"","");
            icon = icon.replace("\"","");
            icon += ".png",
            iconSrc = "http://openweathermap.org/img/w/" + icon,
            html += '<tr> <th>' + forecastEntry.dt_txt + '</th> <th>' 
                + '<img src="' + iconSrc + '"/>' + '</th> <th>'
                + forecastEntry.main.temp + '</th> <th>' 
                + forecastEntry.main.humidity +  '</th> <th>' 
                + forecastEntry.clouds.all + '</th> <th>' 
                + forecastEntry.main.pressure +'</th>';
            html += '</tr>';
        });
        html += '</tbody> </table> </br> <img src="images/giraffe.png"/>';
        $('#log').html(html);
    });
}

function updateTextInput(val) {
    document.getElementById('cnt-value').value=val; 
}
