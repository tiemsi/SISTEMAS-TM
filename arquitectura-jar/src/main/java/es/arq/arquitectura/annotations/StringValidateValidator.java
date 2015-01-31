package es.arq.arquitectura.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

//import java.util.regex.Matcher;
//import java.util.regex.Pattern;


public class StringValidateValidator implements ConstraintValidator<StringValidate,String> {

	String cadena;

	boolean permitirVacio;
	boolean permitirNA;
	String textoNA;
	String caracteresPermitidos;
	String caracteresNoPermitidos;
	String regexp;
	long minLength;
	long maxLength;
	boolean soloNumeros;
	boolean soloLetras;
	boolean permitirSimbolos;
	
	public void initialize(StringValidate constraintAnnotation) {
		permitirVacio			= constraintAnnotation.permitirVacio();
		permitirNA				= constraintAnnotation.permitirNA();
		textoNA					= constraintAnnotation.textoNA();
		caracteresPermitidos	= constraintAnnotation.caracteresPermitidos();
		caracteresNoPermitidos	= constraintAnnotation.caracteresNoPermitidos();
		regexp					= constraintAnnotation.regexp();
		minLength				= constraintAnnotation.minLength();
		maxLength				= constraintAnnotation.maxLength();
		soloNumeros				= constraintAnnotation.soloNumeros();
		soloLetras				= constraintAnnotation.soloLetras();
		permitirSimbolos		= constraintAnnotation.permitirSimbolos();
	}
	
	public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
		
		this.cadena=object;
		
		if(!permitirVacio)
			if(!validaVacio())
				return false;
		
		if(permitirNA)
			if(validaNA())
				return true;
		
		if(caracteresPermitidos.length()>0)
			if(!validaCaracteresPermitidos())
				return false;
		
		if(caracteresNoPermitidos.length()>0)
			if(!validaCaracteresNoPermitidos())
				return false;
		
		if(regexp.length()>0)
			if(!validaRegexp())
				return false;
		
		if(minLength>=0)
			if(!validaMinLength())
				return false;
		
		if(maxLength>=0)
			if(!validaMaxLength())
				return false;
		
		if(soloNumeros)
			if(!validaSoloNumeros())
				return false;
		
		if(soloLetras)
			if(!validaSoloLetras())
				return false;
		
		if(!permitirSimbolos)
			if(!validaSinSimbolos())
				return false;
		
		return true;
	}

	private boolean validaVacio(){
		if(cadena==null)
			return false;
		
		return cadena.length()>0;
	}
	
	private boolean validaNA(){
		return cadena.toUpperCase().equals(textoNA.toUpperCase());
	}
	
	private boolean validaCaracteresPermitidos(){
		for(Character car : caracteresPermitidos.toCharArray()){
			if(!cadena.contains(car.toString()))
				return false;
		}
		return true;
	}
	
	private boolean validaCaracteresNoPermitidos(){
		for(Character car : caracteresPermitidos.toCharArray()){
			if(cadena.contains(car.toString()))
				return false;
		}
		return true;
	}
	
	private boolean validaRegexp(){
		return cadena.matches(regexp);
	}
	
	private boolean validaMinLength(){
		return cadena.length()>=minLength;
	}
	
	private boolean validaMaxLength(){
		return cadena.length()<=maxLength;
	}
	
	private boolean validaSoloNumeros(){
		return cadena.matches("^[0-9]*$");
	}
	
	private boolean validaSoloLetras(){
		return cadena.matches("^[A-z]*$");
	}
	
	private boolean validaSinSimbolos(){
		return cadena.matches("^[A-z0-9 ]*$");
	}
}