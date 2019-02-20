package com.luckwine.parent.violation;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * zyj
 */
public class DubboConstraintViolationException extends ConstraintViolationException {
    private Set<? extends ConstraintViolation<?>> constraintViolations;
    public DubboConstraintViolationException() {
        super(null);
    }
    public DubboConstraintViolationException(Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(constraintViolations);
        this.constraintViolations = constraintViolations;
    }
    public Set<ConstraintViolation<?>> getConstraintViolations() {
        return (Set<ConstraintViolation<?>>) constraintViolations;
    }
    public void setConstraintViolations(Set<? extends ConstraintViolation<?>> constraintViolations) {
        this.constraintViolations = constraintViolations;
    }
}
