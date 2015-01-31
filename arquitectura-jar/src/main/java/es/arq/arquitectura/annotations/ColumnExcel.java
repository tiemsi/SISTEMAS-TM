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

import org.apache.poi.ss.usermodel.CellStyle;


@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = ColumnExcelValidator.class)
@Documented
public @interface ColumnExcel {
	
	String message() default "Columna Excel con propiedades";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	boolean visible() default true;
	String headerName() default "";
	short alineacion() default CellStyle.ALIGN_LEFT;
	boolean numerico() default false;
	String formato() default "?";
}
