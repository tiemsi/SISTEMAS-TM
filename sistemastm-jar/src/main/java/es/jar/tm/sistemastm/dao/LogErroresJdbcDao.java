package es.jar.tm.sistemastm.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public interface LogErroresJdbcDao {
	
	Map<String, Object> executeProcedure(String procedureName, BigDecimal id);
	
	List<Object> executeListQuery(String schemaName, String procedureName, Map<String, Object> params) throws Exception;
	
}
