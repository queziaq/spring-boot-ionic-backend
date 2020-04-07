package com.quezia.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.quezia.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void SendEmail(SimpleMailMessage msg);
	
}
