package com.dmd.favoriteplacesjavaversion.enums;

public enum FirebaseFirestoreCollections {
    POSTS("Posts"),
    SOUNDS("Sound"),
    VIDEOS("Videos"),
    OTHER("Other"),
    IMAGES("Images");

    private final String name;

    private FirebaseFirestoreCollections(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
