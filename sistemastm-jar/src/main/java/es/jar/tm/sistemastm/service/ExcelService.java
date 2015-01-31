package es.jar.tm.sistemastm.service;

import java.util.List;

import es.jar.tm.sistemastm.domain.LogErrores;

public interface ExcelService {

	/**
	 * Método que busca todos los registros de la tabla. 
	 * En este servicio se ejecuta toda la lógica de negocio relativa a dicho proceso.
	 * 
	 * @return
	 */
	List<LogErrores> findAll() throws Exception;
}
