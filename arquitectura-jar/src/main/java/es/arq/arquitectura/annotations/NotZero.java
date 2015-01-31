package es.arq.arquitectura.annotations;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = NotZeroValidator.class)
@Documented
public @interface NotZero {

	 String message() default "El valor No puede ser Cero";
	 Class<?>[] groups() default {};
	 Class<? extends Payload>[] payload() default {};
	
}
