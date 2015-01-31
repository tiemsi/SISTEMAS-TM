package es.arq.arquitectura.security;

import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.x509.SubjectDnX509PrincipalExtractor;
import org.springframework.security.web.authentication.preauth.x509.X509PrincipalExtractor;


/**
 * Clase que lee el certificado X509 que le envia apache.
 * Se ha generado un método dummy para desarrollar en local.
 * El modo de autentificación dummy se lanza si la propiedad
 * mode tiene el valor de "DESARROLLO"
 * @author everis
 * @version 1.0
 */
public class X509AuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {
    @SuppressWarnings("unused")
	private X509PrincipalExtractor principalExtractor = new SubjectDnX509PrincipalExtractor();

    private String mode;
    
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) throws BadCredentialsException{
        X509Certificate cert = extractClientCertificate(request);

        if (cert == null) {
            return null;
        }
        
        try{
        	cert.checkValidity();
        }catch (Exception e){throw new BadCredentialsException(e.getMessage());}
        	
        return cert.getSubjectDN().getName();
    }
 
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return extractClientCertificate(request);
    }

    /**
     * Metodo que recupera el certificado del request
     * @param request
     * @return el certificado del cliente
     */
    private X509Certificate extractClientCertificate(HttpServletRequest request) {
        
    	if (mode !=null && mode.equals("DESARROLLO")){
    		generaCertificadoDummy(request);
    	}
    	
    	X509Certificate[] certs = (X509Certificate[]) request.getAttribute("javax.servlet.request.X509Certificate");
    	if (certs != null && certs.length > 0) {
    		if (logger.isDebugEnabled()) {
    			logger.debug("X.509 client authentication certificate:" + certs[0]);
    		}
    		return certs[0];
    	 }

    	if (logger.isDebugEnabled()) {
    		logger.debug("No client certificate javax.servlet.request.X509Certificate found in request.");	
    	}
    		
    		
    		
    	return null;
    }

    public void setPrincipalExtractor(X509PrincipalExtractor principalExtractor) {
        this.principalExtractor = principalExtractor;
    }

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
    
	
	/**
	 * Este método genera un certificado dummy y lo guarda en el
	 * request de la misma forma que lo guarda apache
	 * @param request
	 */
	private void generaCertificadoDummy(HttpServletRequest request){
		try{
    	    String cacert = "C:\\FNMTClase2CA.cer";
    	    CertificateFactory cf = CertificateFactory.getInstance("X.509");
    	    java.io.FileInputStream in1 = new java.io.FileInputStream(cacert);
    	    java.security.cert.Certificate cac = cf.generateCertificate(in1);
    	    X509Certificate[] arrayCert = new X509Certificate[1];
    	    arrayCert[0]=(java.security.cert.X509Certificate)cac;
    	    request.setAttribute("javax.servlet.request.X509Certificate", arrayCert);
    	    
    	}catch(Exception e){e.printStackTrace();}
	}
    
}
