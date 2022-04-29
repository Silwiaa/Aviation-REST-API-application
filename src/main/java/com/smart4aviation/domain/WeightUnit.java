package com.smart4aviation.domain;

public enum WeightUnit {
    kg("kg"),
    lb("lb");

    private String weightUnit;

     WeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String getWeightUnit() {
         return weightUnit;
    }
}
