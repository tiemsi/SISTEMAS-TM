package es.arq.arquitectura.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ColumnExcelValidator implements ConstraintValidator<ColumnExcel,String> {

	boolean	visible;
	String	headerName;
	short alineacion;
	boolean numerico;
	String formato;
	
	public void initialize(ColumnExcel constraintAnnotation) {
		visible = constraintAnnotation.visible();
		headerName = constraintAnnotation.headerName();
		alineacion = constraintAnnotation.alineacion();
		numerico = constraintAnnotation.numerico();
		formato = constraintAnnotation.formato();
	}

	public boolean isValid(String value, ConstraintValidatorContext context) {
		return true;
	}
}