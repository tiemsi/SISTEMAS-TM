package es.arq.arquitectura.annotations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailStrictValidator implements ConstraintValidator<EmailStrict,String> {

	boolean	permitirNA;
	String	textoNA;
	
	public void initialize(EmailStrict constraintAnnotation) {
		permitirNA = constraintAnnotation.permitirNA();
		textoNA = constraintAnnotation.textoNA();
	}
	
	public boolean isValid(String object, ConstraintValidatorContext constraintContext) {

        return isEmail(object);

	}

	private boolean isEmail(String email) {
	
		Pattern pattern;
		Matcher matcher;
	 
		String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	 
		pattern = Pattern.compile(EMAIL_PATTERN);
			
		matcher			= pattern.matcher(email);
		boolean esMail	= matcher.matches();
		boolean esNa	= email.equals(textoNA);
		
		if(permitirNA){
			return (esMail||esNa);
		}else{
			return esMail;
		}
		
	}
}