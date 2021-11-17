package com.neighbor.userservice.constraints.legends;

public enum Damage {
    SPLIT("SPLIT ENDS"),  // split ends
    DYE("DYE"),    //overprocessed hair usually by dying
    HEAT("HEAT"),   //heat damage
    MECHANICAL("MECHANICAL"), //the way you handle your hair
    HARD("HARD WATER"),   //hard water damage
    SUN("SUN"),
    STRESS("STRESS"),
    CHEMICAL("CHEMICAL"),
    BREAKAGE("BREAKAGE");

    public final String label;

    private Damage(String label){
        this.label = label;
    }

    public static Boolean find(String label) {
        for (Damage e : values()) {
            if (e.label.equals(label)) {
                return true;
            }
        }
        return false;
    }
}
