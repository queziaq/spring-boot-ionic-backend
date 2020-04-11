package com.quezia.cursomc.resources;


import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.quezia.cursomc.domain.Pedido;
import com.quezia.cursomc.services.PedidoService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService serv;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable Integer id) throws ObjectNotFoundException {
		
		Pedido ped = serv.buscar(id);
		
		return ResponseEntity.ok().body(ped);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido ped) throws ObjectNotFoundException{
		ped = serv.insert(ped);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(ped.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<Pedido>> FindPage(
			@RequestParam(value="page" ,defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage" ,defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue ="instance")String orderBy, 
			@RequestParam(value="direction" ,defaultValue="DESC")String direction) {
		Page<Pedido> list = serv.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}

}
