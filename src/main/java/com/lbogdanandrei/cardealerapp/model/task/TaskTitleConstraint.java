package com.lbogdanandrei.cardealerapp.model.task;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = TaskTitleValidator.class)
@Target( {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TaskTitleConstraint {
    String message() default "Invalid title";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

