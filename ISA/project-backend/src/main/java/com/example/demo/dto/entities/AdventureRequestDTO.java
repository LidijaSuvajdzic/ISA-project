package com.example.demo.dto.entities;

import com.example.demo.model.entities.Address;

import java.util.Set;

public class AdventureRequestDTO {
    private String nameOfAdventure;
    private int maxNumberOfPeople;
    private String instructorBiografy;
    private String promoDescription;
    private String fishingEquipment;
    private String cancelationType;
    private String rules;
    private String street;
    private String number;
    private String city;
    private String country;
    private Set<String> images;

    public Set<String> getImages() {
        return images;
    }

    public void setImages(Set<String> images) {
        this.images = images;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getNameOfAdventure() {
        return nameOfAdventure;
    }

    public void setNameOfAdventure(String nameOfAdventure) {
        this.nameOfAdventure = nameOfAdventure;
    }

    public int getMaxNumberOfPeople() {
        return maxNumberOfPeople;
    }

    public void setMaxNumberOfPeople(int maxNumberOfPeople) {
        this.maxNumberOfPeople = maxNumberOfPeople;
    }

    public String getInstructorBiografy() {
        return instructorBiografy;
    }

    public void setInstructorBiografy(String instructorBiografy) {
        this.instructorBiografy = instructorBiografy;
    }

    public String getPromoDescription() {
        return promoDescription;
    }

    public void setPromoDescription(String promoDescription) {
        this.promoDescription = promoDescription;
    }

    public String getFishingEquipment() {
        return fishingEquipment;
    }

    public void setFishingEquipment(String fishingEquipment) {
        this.fishingEquipment = fishingEquipment;
    }

    public String getCancelationType() {
        return cancelationType;
    }

    public void setCancelationType(String cancelationType) {
        this.cancelationType = cancelationType;
    }

    public AdventureRequestDTO() {
    }

    public AdventureRequestDTO(String nameOfAdventure,String rules, int maxNumberOfPeople, String instructorBiografy, String promoDescription, String fishingEquipment, String cancelationType, String street,String number,String city,String country) {
        this.nameOfAdventure = nameOfAdventure;
        this.maxNumberOfPeople = maxNumberOfPeople;
        this.instructorBiografy = instructorBiografy;
        this.promoDescription = promoDescription;
        this.fishingEquipment = fishingEquipment;
        this.cancelationType = cancelationType;
        this.rules = rules;
        this.street=street;
        this.number=number;
        this.city=city;
        this.country=country;
    }
}
