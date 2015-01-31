package es.arq.arquitectura.annotations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import es.arq.arquitectura.util.ValidatorCif;

public class NifValidator implements ConstraintValidator<Nif,String> {

	public void initialize(Nif constraintAnnotation) {
	}
	
	public boolean isValid(String object, ConstraintValidatorContext constraintContext) {

        return (isNIF(object) || isCIF(object));

	}

	private boolean isNIF(String nif) {
	
        if (nif == null) return true;

        if (nif.toUpperCase().startsWith("X") || nif.toUpperCase().startsWith("Y") || nif.toUpperCase().startsWith("Z"))
            nif = nif.substring(1);

        Pattern nifPattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");
        Matcher m = nifPattern.matcher(nif);
        
        if (m.matches()) {
            String letra = m.group(2);
            // Extraer letra del NIF
            String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
            int dni = Integer.parseInt(m.group(1))%23;
            String reference = letras.substring(dni, dni + 1);

            if (reference.equalsIgnoreCase(letra)) return true;
            else return false;
                
        } else {
            return false;
        }
	}

	public static boolean isCIF(String cif) {
					
			return ValidatorCif.isCifValido(cif);         

	}
}
