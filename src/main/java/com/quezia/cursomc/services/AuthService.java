package com.quezia.cursomc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.quezia.cursomc.domain.Cliente;
import com.quezia.cursomc.repositories.ClienteRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository cr;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EmailService emailService;
	
	private Random rand = new Random();
	
	public void sendNewPassword(String email) throws ObjectNotFoundException {
		
		Cliente cliente = cr.findByEmail(email);
		
		if(cliente == null) {
			throw new ObjectNotFoundException("Email  Não Encontrado");
		}
		
		String newPass = newPassword();
		cliente.setSenha(pe.encode(newPass));
		
		cr.save(cliente);
		emailService.sendNewPasswordEmail(cliente, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for(int i = 0; i<10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}
	
	private char randomChar() {
		int opt = rand.nextInt(3);
		if(opt == 0) {
			return (char) (rand.nextInt(10)+48);
		}
		else if(opt == 1) {
			return (char) (rand.nextInt(10)+65);
		}
		else {
			return (char) (rand.nextInt(10)+97);
		}
	}
	
}
