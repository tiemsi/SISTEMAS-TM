package es.jar.tm.sistemastm.service;

import java.security.Principal;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import es.jar.tm.sistemastm.state.StateProcessor;

@Service("stateProcessorService")
public class StateProcessorServiceImpl implements StateProcessorService,
		ApplicationContextAware {

	ApplicationContext ctx;

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		ctx = applicationContext;
	}
	
	public void processStates(Object object, String nuevoEstado, Principal principal) {
		StateProcessor state = (StateProcessor) ctx.getBean("estado"
															.concat("To")
															.concat(nuevoEstado));
		state.action(object, nuevoEstado, principal);
	}
}