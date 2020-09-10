package com.dmd.favoriteplacesjavaversion.enums;

public enum FileExtensions {
    JPG(".jpg");

    private final String name;

    private FileExtensions(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}