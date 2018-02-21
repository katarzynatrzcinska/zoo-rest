/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zoo.resources;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;
import java.io.IOException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Kasia
 */

@Path("/geo")
public class GeoApiResource {
    private static final String GOOGLE_KEY = "AIzaSyDhcQF6tW9Na5oGBpkIBmQquZfeX33Y1ss";
    private static final GeoApiContext geoApiContext = new GeoApiContext().setApiKey(GOOGLE_KEY);
    private static final String TRAVEL_BY_CAR = "samochód";
    private static final String TRAVEL_BY_BICYCLE = "rower";
    private static final String TRAVEL_BY_TRANSIT = "komunikacja publiczna";
    private static final String TRAVEL_BY_WALKING = "pieszo";
    private static final String METRES = "metry";
    private static final String MILES = "mile";
        
    DistanceMatrixApiRequest req;
    
    @GET
    @Path("distance")
    @Produces(MediaType.APPLICATION_JSON)
    public DistanceMatrix getDistance(
            @QueryParam("origin") String origin,
            @QueryParam("destination") String destination,
            @QueryParam("travel_mode") String travel_mode,
            @QueryParam("units") String units) throws ApiException, InterruptedException, IOException {

            req = DistanceMatrixApi.newRequest(geoApiContext);
            DistanceMatrix matrix = req.origins(origin)
                    .destinations(destination)
                    .mode(getTravelMode(travel_mode))
                    .units(getUnitSystem(units))
                    .language("pl-PL")
                    .await();
            
            return matrix;
    }        

    private TravelMode getTravelMode(String travel_mode) {
        switch (travel_mode) {
            case TRAVEL_BY_CAR:
                return TravelMode.DRIVING;
            case TRAVEL_BY_BICYCLE:
                return TravelMode.BICYCLING;
            case TRAVEL_BY_TRANSIT:
                return TravelMode.TRANSIT;
            case TRAVEL_BY_WALKING:
                return TravelMode.WALKING;
        }
        return null;
    }
    
    private Unit getUnitSystem(String units) {
        switch (units) {
            case METRES:
                return Unit.METRIC;
            case MILES:
                return Unit.IMPERIAL;
        }
        return null;
    }  
}