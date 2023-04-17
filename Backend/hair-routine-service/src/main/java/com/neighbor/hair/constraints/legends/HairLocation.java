package com.neighbor.hair.constraints.legends;

public enum HairLocation {
    SCALP,
    ENDS,
    STRAND,
    BASE;

    public static Boolean find(String label) {
        for (HairLocation e : values()) {
            if (e.valueOf(label) != null) {
                return true;
            }
        }
        return false;
    }
}
