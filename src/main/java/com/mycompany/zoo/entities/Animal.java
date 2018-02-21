/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zoo.entities;

/**
 *
 * @author Kasia
 */
public class Animal {
    private Integer id;
    private String name;
    private String spieces;
    private String place;
    private String oddity;

    public Animal() {}

    public Animal(Integer id, String name, String spieces, String place, String oddity) {
        this.id = id;
        this.name = name;
        this.spieces = spieces;
        this.place = place;
        this.oddity = oddity;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setSpieces(String spieces) {
        this.spieces = spieces;
    }
    
    public String getSpieces() {
        return spieces;
    }
    
    public void setPlace(String place) {
        this.place = place;
    }
    
    public String getPlace() {
        return place;
    }
    
    public void setOddity(String oddity) {
        this.oddity = oddity;
    }
    
    public String getOddity() {
        return oddity;
    }
}