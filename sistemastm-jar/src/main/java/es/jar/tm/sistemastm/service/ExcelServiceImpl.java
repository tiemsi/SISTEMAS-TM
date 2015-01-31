package es.jar.tm.sistemastm.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import es.jar.tm.sistemastm.domain.LogErrores;

@Service("excelService")
@Transactional
public class ExcelServiceImpl implements ExcelService {

	@Autowired
	private LogErroresService logErrores;

	@Transactional(readOnly = true)
	public List<LogErrores> findAll() throws Exception {
		return Lists.newArrayList(logErrores.findById(BigDecimal.ONE));
	}
}
