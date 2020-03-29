package com.quezia.cursomc.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.quezia.cursomc.domain.Cliente;
import com.quezia.cursomc.services.ClienteService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService serv;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable Integer id) throws ObjectNotFoundException {
		
		Cliente cli = serv.buscar(id);
		
		return ResponseEntity.ok().body(cli);
	}

}
