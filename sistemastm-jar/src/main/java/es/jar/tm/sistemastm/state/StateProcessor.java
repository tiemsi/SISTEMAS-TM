package es.jar.tm.sistemastm.state;

import java.security.Principal;

import org.springframework.transaction.annotation.Transactional;


public interface StateProcessor {

	/**
	 * Motor de estados para controlar flujos de aplicación.
	 * 
	 * @param object
	 * @param nuevoEstado
	 * @param principal
	 */
	@Transactional(readOnly = false)
	void action(Object object, String nuevoEstado, Principal principal);
	
}