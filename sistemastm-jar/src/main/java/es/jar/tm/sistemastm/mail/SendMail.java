package es.jar.tm.sistemastm.mail;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import es.jar.tm.sistemastm.domain.Mail;


public class SendMail {

	public SendMail(){}
	
	public SendMail(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	private MailSender mailSender;
	 
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public void sendMail(Mail mail) {
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom(mail.getOrigen());
		message.setTo(mail.getDestino().split("[,;\\s\\-\\?]"));
		message.setSubject(mail.getSubject());
		message.setText(mail.getTexto());
		mailSender.send(message);
	}
	
	public void envioMail(Mail mail){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
		SendMail sMail = (SendMail) context.getBean("sendMail");
    	sMail.sendMail(mail);
	}
}
