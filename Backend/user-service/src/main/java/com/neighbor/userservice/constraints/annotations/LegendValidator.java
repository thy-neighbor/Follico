package com.neighbor.userservice.constraints.annotations;

import com.neighbor.userservice.constraints.legends.Damage;
import com.neighbor.userservice.constraints.legends.LegendType;
import com.neighbor.userservice.constraints.legends.Porosity;

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

        switch (type){
            case DAMAGE:
                if(Damage.find(value))
                    return true;
            case POROSITY:
                if (Porosity.find(value))
                    return true;
            default:
                    return false;
        }

    }
}
