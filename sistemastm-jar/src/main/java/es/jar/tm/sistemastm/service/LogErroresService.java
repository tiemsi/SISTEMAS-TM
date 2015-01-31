package es.jar.tm.sistemastm.service;

import java.math.BigDecimal;

import es.jar.tm.sistemastm.domain.LogErrores;

public interface LogErroresService {

	/**
	 * @param id
	 * @return
	 */
	LogErrores findById(BigDecimal id) throws Exception;
}
