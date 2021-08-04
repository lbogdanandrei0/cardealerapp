package com.lbogdanandrei.cardealerapp.model.task;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TaskTitleValidator implements ConstraintValidator<TaskTitleConstraint, String> {
    @Override
    public void initialize(TaskTitleConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String taskTitle, ConstraintValidatorContext constraintValidatorContext) {
        return taskTitle.trim().length() != 0;
    }
}
