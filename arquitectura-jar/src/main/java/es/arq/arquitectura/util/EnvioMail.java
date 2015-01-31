package es.arq.arquitectura.util;


public class EnvioMail {
	
//	private MailSender mailSender;
//	 
//	public void setMailSender(MailSender mailSender) {
//		this.mailSender = mailSender;
//	}
// 
//	public void sendMail() {
//		SimpleMailMessage message = new SimpleMailMessage();
//		
//		message.setFrom("from");
//		message.setTo("to".split("[,;\\s\\-\\?]"));
//		message.setSubject("subject");
//		message.setText("msg");
//		mailSender.send(message);
//	}
//	
//	public void envioMailA(String msg){
////		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
////    	EnvioMail mail = (EnvioMail) context.getBean("envioMail");
////    	mail.sendMail(new Object(),
////	    			  "Constantes.MAIL_FROM_ICO",
////	    			  PropertiesUtil.getProperty("mail.subject.ico"),
////	    			  msg);
//	}
//	
//	public void envioMailADestinatario(String msg, String destinatario){
////		SimpleMailMessage message = new SimpleMailMessage();
////		message.setFrom(Constantes.MAIL_FROM_ICO);
////		message.setTo(destinatario.split("[,;\\s\\-\\?]"));
////		message.setSubject(Constantes.MAIL_SUBJECT_ICO);
////		message.setText(msg);
////		mailSender.send(message);
//	}
	
	/**
	 * Este método genera un mensaje (según el tipoMensaje recibido) ya rellenado (a partir de elementoDatos) 
	 * y lo envía a la dirección o direcciones que se reciba/n por parámetro (destinatario)
	 * 
	 * @param tipoMensaje   ID del tipo de mensaje que se va a enviar, esta ID corresponde a la tabla MENSAJE de base de datos.
	 * @param idioma        ID del idioma en el que se debe enviar el mensaje, esta ID corresponde a la tabla IDIOMA de base de datos
	 * @param elementoDatos Objeto del que se van a obtener los datos con los que se rellenarán los huecos del mensaje (como ID de garantía o prórroga, etc...)
	 * @param destinatario  Cadena con la dirección (o direcciones, separadas por espacios en blanco o los caracteres entre paréntesis (, ; - ?)  
	 */
}
