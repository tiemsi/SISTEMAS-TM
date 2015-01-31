package es.jar.tm.sistemastm.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import es.jar.tm.sistemastm.dao.LogErroresJdbcDao;
import es.jar.tm.sistemastm.domain.LogErrores;

@Service("LogErroresService")
@Transactional
public class LogErroresServiceImpl implements LogErroresService {

	@Autowired
	private LogErroresJdbcDao logErroresJdbcDao;
	

	@Transactional(readOnly = true)
	public LogErrores findById(BigDecimal id) throws Exception {
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("p_id", id);
//		params.put("o_id", id);
//		params.put("o_descripcion", "descripcion");
//		params.put("o_origen", "origen");
//		params.put("o_traza", "traza");
//		params.put("o_usuario", "EXT2086");
//		params.put("o_fecha", new Date());
//		params.put("o_parametros", "parametros");
//		logErroresJdbcDao.executeListQuery("PROTOTIPO", "SP_LOG_ERRORES_FIND_ALL", params);
		
		Map<String, Object> map= logErroresJdbcDao.executeProcedure("SP_LOG_ERRORES_FIND_BY_ID", id);
		LogErrores logErrores = new LogErrores();
		logErrores.setId(BigDecimal.valueOf(Long.parseLong(map.get("O_ID").toString())));
		logErrores.setDescripcion(map.get("O_DESCRIPCION").toString());
		
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		logErrores.setFecha(sdf.parse(map.get("O_FECHA").toString()));
		
		logErrores.setOrigen(map.get("O_ORIGEN").toString());
		logErrores.setParametros(map.get("O_PARAMETROS").toString());
		logErrores.setTraza(map.get("O_TRAZA").toString());
		logErrores.setUsuario(map.get("O_USUARIO").toString());
		
		return logErrores;
//		return logErroresDao.findById(id);
	}

}
