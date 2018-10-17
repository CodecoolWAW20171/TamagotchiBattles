package com.codecool.tamagotchi.enumerations;

public enum Type {
    FIRE("fire"),
    WATER("water"),
    EARTH("earth");

    private final String type;

    Type(String groupType) {
        this.type = groupType;
    }

    public String getGroupType() {
        return type;
    }
}
