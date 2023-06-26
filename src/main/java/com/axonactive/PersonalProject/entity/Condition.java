package com.axonactive.PersonalProject.entity;

public enum Condition {
    LOST(0),
    NORMAL(1);
    private int value;
    Condition(int value){this.value = value;}
    public int getValue(){return value;}
}
