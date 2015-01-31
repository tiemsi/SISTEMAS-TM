package es.arq.arquitectura.annotations;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotZeroValidator implements ConstraintValidator<NotZero, Number> {

        public void initialize(NotZero constraintAnnotation) {
                // nothing to do
        }

        public boolean isValid(Number object,
                        ConstraintValidatorContext constraintContext) {

                return object == null || object.floatValue() != 0.0;

        }

}