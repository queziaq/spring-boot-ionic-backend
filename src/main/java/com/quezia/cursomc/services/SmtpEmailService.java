package com.quezia.cursomc.services;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SmtpEmailService extends AbstractEmailService{

	@Autowired
	private MailSender mailSender;
	
	private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(MockEmailService.class);
	
	@Override
	public void SendEmail(SimpleMailMessage msg) {
		LOG.info("Simulado Envio de Email");
		mailSender.send(msg);
		LOG.info("Email Enviado");
		
	}

}
