package es.arq.arquitectura.annotations;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = StringValidateValidator.class)
@Documented
public @interface StringValidate {

	String message() default "Formato Incorrecto";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
	boolean	permitirVacio()				default false;
	boolean	permitirNA()				default false;
	String	textoNA()					default "NA";
	String	caracteresPermitidos()		default "";
	String	caracteresNoPermitidos()	default "";
	String	regexp() 					default "";
	long	minLength()					default -1;
	long	maxLength()					default	-1;
	boolean	soloNumeros()				default false;
	boolean	soloLetras()				default false;
	boolean	permitirSimbolos()			default true;
}
