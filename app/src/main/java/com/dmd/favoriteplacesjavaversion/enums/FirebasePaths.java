package com.dmd.favoriteplacesjavaversion.enums;

public enum FirebasePaths {
    IMAGES("images/"),
    SOUNDS("sounds/"),
    VIDEOS("videos/"),
    PROFILE_PHOTOS("images/profile_images/");

    private final String name;

    private FirebasePaths(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}