/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function getDistance() {
    var origin = $("input[name='origin']").val();
    var destination = "Gdańsk";
    var travel_mode = $("select[name='travel_mode']").val();
    var units = $("select[name='units']").val();

    $.getJSON("resources/geo/distance", {origin: origin, destination: destination, 
              travel_mode: travel_mode, units: units}, function (data) {
        //console.log(data);
        html = ''; 
        if (data.originAddresses[0].length == 0)
            html += '<p class="bodyTitle">' + "Nie można pobrać informacji - wpisane miasto nie istnieje. " + '</p>';
        else if(data.rows[0].elements[0].distance == null) 
            html += '<p class="bodyTitle">' + "Nie można pobrać informacji - dojazd jest niemożliwy wybranym środkiem transportu. " + '</p>';
        else {
            html += '<table style="width:100%" > <thead> <tr>' + '<th>' 
                + "Miasto początkowe" + '</th> <th>' 
                + "Czas przejazdu" + '</th> <th>' 
                + "Długość trasy" + '</th> </tr> </thead> <tbody>'
                +'<tr> <th>' + data.originAddresses[0] + '</th> <th>' 
                + data.rows[0].elements[0].duration.humanReadable + '</th> <th>' 
                + data.rows[0].elements[0].distance.humanReadable +  '</th> </tr> </tbody> </table>';
        }
        html += '<img src="images/monkey.png"/>';
        $('#log').html(html);
    });
}

