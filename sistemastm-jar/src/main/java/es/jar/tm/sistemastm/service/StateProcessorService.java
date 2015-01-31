package es.jar.tm.sistemastm.service;

import java.security.Principal;

public interface StateProcessorService {

	/**
	 * Motor de estados para controlar flujos de aplicación.
	 * 
	 * @param object
	 * @param nuevoEstado
	 * @param principal
	 */
	void processStates(Object object, String nuevoEstado, Principal principal);

}
