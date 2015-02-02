package com.ervacon.spring2sample.domain;

import java.util.ArrayList;
import java.util.List;

public class User implements Cloneable {

    private Long id;
    private String firstName;
    private String lastName;
    private String notes;
    private String house;
    private Country country;
    private Colour favouriteColour = Colour.RED;
    private char sex;
    private Preferences preferences = new Preferences();
    private List<Skill> skills = new ArrayList<Skill>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Colour getFavouriteColour() {
        return favouriteColour;
    }

    public void setFavouriteColour(Colour favouriteColour) {
        this.favouriteColour = favouriteColour;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
    
    public Object clone() throws CloneNotSupportedException {
    	return super.clone();
    }

}
