/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zoo.resources;

import com.mycompany.zoo.entities.Animal;
import com.mycompany.zoo.servlet.AnimalContext;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Kasia
 */

@Path("/animals")
public class AnimalResource {
    public static final String ANIMAL_CONTEXT = "animalContext";

    @Context
    ServletContext context;

    @Context
    HttpServletRequest request;

    @Context
    HttpServletResponse response;
    
    public static final String SPIECES_DEFAULT = "wszystkie";
    public static final String PLACE_DEFAULT = "wszędzie";
    
    private AnimalContext getAnimalContext() {
        AnimalContext animalContext = (AnimalContext) context.getAttribute(ANIMAL_CONTEXT);
        if (animalContext == null) {
            try {
                animalContext = new AnimalContext();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AnimalResource.class.getName()).log(Level.SEVERE, null, ex);
            }
            context.setAttribute(ANIMAL_CONTEXT, animalContext);
        }
        return animalContext;
    }
    
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Animal> listAnimals(
                @QueryParam("animal_spieces") String animalSpieces,
                @QueryParam("animal_place") String animalPlace) {
        
       List<Animal> animals = getAnimalContext().findAllAnimals();
       if(!animalSpieces.equals(SPIECES_DEFAULT) || !animalPlace.equals(PLACE_DEFAULT)) 
           animals = findAnimalsBySpiecesAndPlace(animals, animalSpieces, animalPlace);
       return animals;
}
    
    public List<Animal> findAnimalsBySpiecesAndPlace(List<Animal> animals, String animalSpieces, String animalPlace) {
        List<Animal> selectedAnimals = new ArrayList<Animal>();
        
        for(Animal animal: animals) {
            if(!animalSpieces.equals(SPIECES_DEFAULT) && !animalPlace.equals(PLACE_DEFAULT)) {
                if(animal.getSpieces().equals(animalSpieces) && animal.getPlace().equals(animalPlace))
                    selectedAnimals.add(animal);
            }
            else if(animalSpieces.equals(SPIECES_DEFAULT) && !animalPlace.equals(PLACE_DEFAULT)) {
                if(animal.getPlace().equals(animalPlace))
                    selectedAnimals.add(animal);
            }
            else if(!animalSpieces.equals(SPIECES_DEFAULT) && animalPlace.equals(PLACE_DEFAULT)) {
                if(animal.getSpieces().equals(animalSpieces))
                    selectedAnimals.add(animal);
            }
        }

        return selectedAnimals;
    }
}