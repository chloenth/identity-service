package edu.dev.identityservice.validator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DobValidator implements ConstraintValidator<DobConstraint, LocalDate> {

	private int min;

	@Override
	public void initialize(DobConstraint dobConstraint) {
		ConstraintValidator.super.initialize(dobConstraint);
		min = dobConstraint.min();
	}

	@Override
	public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
		System.out.println("in isValid of DobValidator");
		
		if (Objects.isNull(value)) {
			return true;
		}

		long years = ChronoUnit.YEARS.between(value, LocalDate.now());

		return years >= min;
	}

}
