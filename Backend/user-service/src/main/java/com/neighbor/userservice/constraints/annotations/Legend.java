package com.neighbor.userservice.constraints.annotations;


import com.neighbor.userservice.constraints.legends.LegendType;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/*
Legend type is an enum created in the legends package that houses the names of our validation types
these types are used to validate using the correct enum value representation
* */
@Constraint(validatedBy = LegendValidator.class)
@Target({ TYPE, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Documented
public @interface Legend {
    String message() default "Legend.NotFound";
    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};

    LegendType value();
}
