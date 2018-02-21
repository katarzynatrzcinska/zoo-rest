/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zoo.servlet;

import com.mycompany.zoo.entities.Animal;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Kasia
 */

public class AnimalContext {
    private final List<Animal> animals;
    
    public AnimalContext() throws FileNotFoundException {
        animals = Collections.synchronizedList(new ArrayList<Animal>());
        Integer id;
        String name;
        String spieces;
        String place;
        String oddity;
        
        try { 
            File f = new File("C:\\Users\\Kasia\\Desktop\\Studia\\Semestr V\\Aplikacje usług internetowych\\Laboratorium\\Lab6\\Zoo\\src\\main\\resources\\data\\zooanimals.txt");
            Scanner sc = new Scanner(f);

            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] details = line.split(";");
                id = Integer.parseInt(details[0]);
                name = details[1];
                spieces = details[2];
                place = details[3];
                oddity = details[4];

                animals.add(new Animal(id, name, spieces, place, oddity));
            }
        } catch (FileNotFoundException e) {         
            e.printStackTrace();
        }
    }
    
    public List<Animal> findAllAnimals() {
        return animals;
    }
}
