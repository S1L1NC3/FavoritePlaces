package com.dmd.favoriteplacesjavaversion.enums;

public enum FirebaseFirestoreFields {
    DOWNLOAD_URL("downloadUrl");

    private final String name;

    private FirebaseFirestoreFields(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
