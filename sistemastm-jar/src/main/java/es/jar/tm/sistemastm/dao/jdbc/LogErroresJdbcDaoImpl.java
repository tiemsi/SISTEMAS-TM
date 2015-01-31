package es.jar.tm.sistemastm.dao.jdbc;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import es.jar.tm.sistemastm.dao.LogErroresJdbcDao;

@Repository("logErroresJdbcDao")
public class LogErroresJdbcDaoImpl implements LogErroresJdbcDao {
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall jdbcCall;
	
	@Resource(name = "dataSource")
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
 
	public DataSource getDataSource() {
		return dataSource;
	}
		
	//FUNCIONA OK
	public Map<String, Object> executeProcedure(String procedureName, BigDecimal id) {
		this.jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName(procedureName);
        SqlParameterSource in = new MapSqlParameterSource().addValue("p_id", id);
        return jdbcCall.execute(in);
    }
	
	//NO FUNCIONA :(
	public List<Object> executeListQuery(String schemaName, String procedureName, Map<String, Object> params) throws Exception{
		jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcCall = new SimpleJdbcCall(jdbcTemplate);
		jdbcCall.withSchemaName(schemaName);
		jdbcCall.withProcedureName(procedureName);
		
		if (params != null){
			for (Map.Entry<String, Object> param : params.entrySet()){
				jdbcCall.addDeclaredParameter(new SqlParameter(param.getKey(), getOracleTypes(param.getValue())));
			}
		}
		
		return (List<Object>) jdbcCall.execute(params).values();
	}
	
	private int getOracleTypes(Object param){
//		int type = OracleTypes.NULL;
//		
//		if (param instanceof String) type = OracleTypes.VARCHAR;
//		else if (param instanceof Date) type = OracleTypes.DATE;
//		else if (param instanceof Integer) type = OracleTypes.INTEGER;
//		else if (param instanceof BigDecimal) type = OracleTypes.NUMBER;
//		else if (param instanceof Long) type = OracleTypes.NUMBER;
//		else if (param instanceof Float) type = OracleTypes.FLOAT;
//		else if (param instanceof Boolean) type = OracleTypes.BOOLEAN;
//		else if (param instanceof Byte) type = OracleTypes.BIT;
//		else if (param instanceof Clob) type = OracleTypes.CLOB;
//		else if (param instanceof Blob) type = OracleTypes.BLOB;
//				
//		return type;		
		return 1;
	}
	
}
