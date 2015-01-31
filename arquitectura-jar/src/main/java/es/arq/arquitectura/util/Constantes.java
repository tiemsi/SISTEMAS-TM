package es.arq.arquitectura.util;

public class Constantes {

	/**
	 * Modo de acceso a la aplicación.
	 */
	public final static String ACCESS_MODE = PropertiesUtil.getProperty("mode");

	/**
	 * URL de la aplicación de seguridad.
	 */
	public final static String URL_APLICACION_SEGURIDAD = PropertiesUtil.getProperty("url.aplicacion.seguridad");
}