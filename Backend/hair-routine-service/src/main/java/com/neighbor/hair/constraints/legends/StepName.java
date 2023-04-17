package com.neighbor.hair.constraints.legends;

public enum StepName {
    PRE("PRE CLEANSE"),         //some people deep condition first, or just prep
    CLEANSE("CLEANSE"),         //shampoo, etc.
    CONDITION("MECHANICAL"),    //conditioner, etc.
    MOISTURIZE("MOISTURIZE"),   //mositurizer, oil, etc.
    DRY("DRY"),                 //towel, blow, etc.
    STYLE("STYLE"),             //gel, wax, etc.
    FINISH("FINISH");           //holding, etc

    public final String label;

    private StepName(String label){
        this.label = label;
    }

    //Find method with string for LegendValidator to validate
    public static Boolean find(String label) {
        for (StepName e : values()) {
            if (e.label.equals(label)) {
                return true;
            }
        }
        return false;
    }

}
