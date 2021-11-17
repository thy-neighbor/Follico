package com.neighbor.userservice.constraints.legends;

public enum Porosity{
    LOW,
    MED,
    HIGH;

    public static Boolean find(String label) {
        for (Porosity e : values()) {
            if (e.valueOf(label) != null) {
                return true;
            }
        }
        return false;
    }
}
