package com.quezia.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService {

	private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(MockEmailService.class);
	@Override
	public void SendEmail(SimpleMailMessage msg) {
		LOG.info("Simulado Envio de Email");
		LOG.info(msg.toString());
		LOG.info("Email Enviado");
		
	}
	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		
		LOG.info("Simulado Envio de Email HTML");
		LOG.info(msg.toString());
		LOG.info("Email Enviado");
	}

}
