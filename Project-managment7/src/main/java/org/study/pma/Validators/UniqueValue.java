package org.study.pma.Validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueValidator.class)
public @interface UniqueValue {

	//custom validator 
	
	String message() default "Unique Constraint violated";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
