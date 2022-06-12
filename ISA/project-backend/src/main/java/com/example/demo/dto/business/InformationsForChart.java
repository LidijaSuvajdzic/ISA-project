package com.example.demo.dto.business;

import java.util.List;

public class InformationsForChart {

    public List<String> names;
    public List<Integer> numberOfPeople;
    public List<Double> grade;
    public List<Integer> earnings;

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public List<Integer> getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(List<Integer> numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public List<Double> getGrade() {
        return grade;
    }

    public void setGrade(List<Double> grade) {
        this.grade = grade;
    }

    public List<Integer> getEarnings() {
        return earnings;
    }

    public void setEarnings(List<Integer> earnings) {
        this.earnings = earnings;
    }

    public InformationsForChart(List<String> names, List<Integer> numberOfPeople, List<Double> grade, List<Integer> earnings) {
        this.names = names;
        this.numberOfPeople = numberOfPeople;
        this.grade = grade;
        this.earnings = earnings;
    }

    public InformationsForChart(){}
}
