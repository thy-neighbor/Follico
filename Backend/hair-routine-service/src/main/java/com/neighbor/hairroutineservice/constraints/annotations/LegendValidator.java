package com.neighbor.hairroutineservice.constraints.annotations;

import com.neighbor.hairroutineservice.constraints.legends.HairLocation;
import com.neighbor.hairroutineservice.constraints.legends.LegendType;
import com.neighbor.hairroutineservice.constraints.legends.StepName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LegendValidator implements ConstraintValidator<Legend, String> {

    private LegendType type;

    //i have to make a enum for the enum names
    @Override
    public void initialize(Legend constraintAnnotation) {
        type = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        switch (type) {
            case HAIRLOCATION:
                if (HairLocation.find(value))
                    return true;
            case STEPNAME:
                if (StepName.find(value))
                    return true;
            default:
                return false;
        }

    }
}
