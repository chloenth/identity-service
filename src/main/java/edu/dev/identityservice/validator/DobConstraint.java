package edu.dev.identityservice.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD })
@Constraint(validatedBy = {DobValidator.class})
@Retention(RetentionPolicy.RUNTIME)
public @interface DobConstraint {
	String message() default "Invalid date of birth";
	
	int min();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
