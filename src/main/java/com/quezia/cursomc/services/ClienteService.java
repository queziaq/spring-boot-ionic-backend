package com.quezia.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quezia.cursomc.domain.Cliente;
import com.quezia.cursomc.repositories.ClienteRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository cr;
	
	public Cliente buscar(Integer id) throws ObjectNotFoundException {
		Optional<Cliente> obj = cr.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " 
		+ id + ", Tipo: " + Cliente.class.getName()));
	}
	
}
