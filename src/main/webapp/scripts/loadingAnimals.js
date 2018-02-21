/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function loadAnimals() {
    var animal_spieces = $("select[name='animal_spieces']").val();
    var animal_place = $("select[name='animal_place']").val();

    $.getJSON('resources/animals/list', {animal_spieces: animal_spieces, animal_place: animal_place}, function (data) {   
        var html = '';
        if(data.length != 0) {
            html += '<table style="width:100%" > <thead> <tr>' + '<th>' 
                    + "Nazwa" + '</th> <th>' 
                    + "Gatunek" + '</th> <th>' 
                    + "Miejsce występowania" + '</th> <th>' 
                    + "Ciekawostka" + '</th> </tr> </thead> <tbody>';
            $.each(data, function (i, animal) {
                html += '<tr> <th>' + animal.name + '</th> <th>' 
                    + animal.spieces + '</th> <th>' 
                    + animal.place +  '</th> <th>' 
                    + animal.oddity +'</th>';
                html += '</tr>';
            });
            html += '</tbody> </table> </br> <img src="images/panda.png"/>';
        }
        else 
            html += '<p class="bodyTitle"> ' + "Brak zwierząt spełniających wybrane kryterium." + '</p> <img src="images/panda.png"/>';

        $('#log').html(html);
    }).error(function (jqXHR) {
        console.log("incoming Text " + jqXHR.responseText);
    });
}

